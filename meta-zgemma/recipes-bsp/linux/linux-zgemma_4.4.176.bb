DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
VER ?= "${@bb.utils.contains('TARGET_ARCH', 'aarch64', '64' , '', d)}"

KERNEL_RELEASE = "4.4.176"
SRCDATE = "20210920"
COMPATIBLE_MACHINE = "^h11$|^h8$|^h8se$|^h9$|^h9combo$|^h9se$|^h9combose$|^h10$|^i55plus$|^i55se$"

inherit kernel machine_kernel_pr

MACHINE_KERNEL_PR = "r0"
MACHINE_KERNEL_PR:append = ".6"

SRC_URI[md5sum] = "261fd4b3d11cf2e593abb1706033fe1c"
SRC_URI[sha256sum] = "6448ea7093cea5e30fcd9ceea357eee3ad98d1b3e7a2367a41fae990f39468e8"

SRC_URI = "http://downloads.openpli.org/archive/hisilicon/linux-${KERNEL_RELEASE}-${SRCDATE}-arm.tar.gz \
	file://defconfig \
	file://initramfs-subdirboot.cpio.gz;unpack=0 \
	file://findkerneldevice.sh \
	file://0101-hi3716mv430-dts-move-aon-gpio7-gpio5.patch \
	file://make-yyloc-declaration-extern-4.4.176.patch \
	file://fix-build-with-binutils-2.41.patch \
"

S = "${WORKDIR}/linux-${PV}"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "tmp"
KERNEL_IMAGETYPE = "uImage"
KERNEL_OUTPUT = "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"

SRC_URI:append:nand = " \
	file://0001-mmc-switch-1.8V.patch \
"

SRC_URI:append:h9se = " \
	file://0001-mmc-switch-1.8V.patch \
"

SRC_URI:append:i55se = " \
	file://0001-mmc-switch-1.8V.patch \
"

S = "${WORKDIR}/linux-${PV}"


FILES:${KERNEL_PACKAGE_NAME}-image = "/${KERNEL_IMAGEDEST}/findkerneldevice.sh"
FILES:${KERNEL_PACKAGE_NAME}-image:nand = " "
RDEPENDS:${KERNEL_PACKAGE_NAME}-image:nand += "mtd-utils"


kernel_do_configure:prepend() {
	install -d ${B}/usr
	install -m 0644 ${WORKDIR}/initramfs-subdirboot.cpio.gz ${B}/
}

INSTALL_FINDKERNELDEVICE = "1"
INSTALL_FINDKERNELDEVICE:nand = "0"

kernel_do_install:append() {
    if [ "${INSTALL_FINDKERNELDEVICE}" = "1" ]; then
        install -d ${D}/${KERNEL_IMAGEDEST}
        install -m 0755 ${WORKDIR}/findkerneldevice.sh ${D}/${KERNEL_IMAGEDEST}
    fi
}

pkg_postinst:kernel-image() {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ] ; then
			/${KERNEL_IMAGEDEST}/./findkerneldevice.sh
			dd if=/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} of=/dev/kernel
		fi
	fi
	true
}

pkg_postinst:kernel-image:nand() {
    if [ -n "$D" ]; then
        exit 0
    fi

    MTD_ENTRY="$(grep -m1 '"kernel"' /proc/mtd || true)"
    MTD_DEVICE="$(printf '%s\n' "$MTD_ENTRY" | cut -d: -f1)"
    if [ -z "$MTD_DEVICE" ]; then
        echo "ERROR: kernel MTD partition not found" >&2
        exit 1
    fi

    MTD_PATH="/dev/$MTD_DEVICE"
    KERNEL_FILE="/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_RELEASE}"
    if [ ! -e "$MTD_PATH" ]; then
        echo "ERROR: MTD device does not exist: $MTD_PATH" >&2
        exit 1
    fi
    if [ ! -f "$KERNEL_FILE" ]; then
        echo "ERROR: kernel image is missing: $KERNEL_FILE" >&2
        exit 1
    fi

    if ! flash_erase "$MTD_PATH" 0 0; then
        echo "ERROR: flash_erase failed for $MTD_PATH" >&2
        exit 1
    fi

    if ! nandwrite -p "$MTD_PATH" "$KERNEL_FILE"; then
        echo "ERROR: nandwrite failed for $KERNEL_FILE to $MTD_PATH" >&2
        exit 1
    fi
    sync
}
