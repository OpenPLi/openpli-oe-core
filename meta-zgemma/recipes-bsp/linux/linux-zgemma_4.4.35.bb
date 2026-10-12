DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
VER ?= "${@bb.utils.contains('TARGET_ARCH', 'aarch64', '64' , '', d)}"

KERNEL_RELEASE = "4.4.35"
SRCDATE = "20200508"
COMPATIBLE_MACHINE = "^h8se$|^h9$|^h9se$|^h9combo$|^h9combose$|^h10$|^i55plus$|^i55se$"

inherit kernel machine_kernel_pr

MACHINE_KERNEL_PR:append = ".9"

SRC_URI[arm.md5sum] = "f9e67e2d0ceab518510413f8f4315bc3"
SRC_URI[arm.sha256sum] = "45ae717b966a74326fd7297d81b3a17fd5b3962b7704170682a615ca7cdec644"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/linux-${PV}-${SRCDATE}-${ARCH}.tar.gz;name=${ARCH} \
	file://defconfig \
	file://0001-remote.patch \
	file://HauppaugeWinTV-dualHD.patch \
	file://dib7000-linux_4.4.179.patch \
	file://dvb-usb-linux_4.4.179.patch \
	file://wifi-linux_4.4.183.patch \
	file://0002-log2-give-up-on-gcc-constant-optimizations.patch \
	file://0003-dont-mark-register-as-const.patch \
	file://0004-linux-fix-buffer-size-warning-error.patch \
	file://0005-xbox-one-tuner-4.4.patch \
	file://0006-dvb-media-tda18250-support-for-new-silicon-tuner.patch \
	file://0007-dvb-mn88472-staging.patch \
	file://Backport_minimal_compiler_attributes_h_to_support_GCC_9.patch \
	file://initramfs-subdirboot.cpio.gz;unpack=0 \
	file://findkerneldevice.sh \
	file://make-yyloc-declaration-extern-4.4.35.patch \
	file://fix-build-with-binutils-2.41.patch \
"

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

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "tmp"
KERNEL_IMAGETYPE = "uImage"
KERNEL_OUTPUT = "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"

FILES:${KERNEL_PACKAGE_NAME}-image = "/${KERNEL_IMAGEDEST}/findkerneldevice.sh"
FILES:${KERNEL_PACKAGE_NAME}-image:nand = " "


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
    if [ -z "$D" ]; then
        MTD_DEVICE="$(grep -m1 '"kernel"' /proc/mtd | cut -d: -f1)"

        if [ -z "$MTD_DEVICE" ]; then
            echo "Kernel MTD partition not found"
            exit 1
        fi

        KERNEL_FILE="/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}"

        if [ -f "$KERNEL_FILE" ]; then
            flash_eraseall "/dev/$MTD_DEVICE" &&
            nandwrite -p "/dev/$MTD_DEVICE" "$KERNEL_FILE"
        fi
    fi
    true
}
