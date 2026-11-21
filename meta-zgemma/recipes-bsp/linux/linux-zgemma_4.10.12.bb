DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

KERNEL_RELEASE = "4.10.12"
COMPATIBLE_MACHINE = "^sh1$|^h3$|^h4$|^h5$|^h6$|^h7$|^h17$|^h17twin$|^lc$|^i55$"

inherit kernel machine_kernel_pr

MACHINE_KERNEL_PR:append = ".4"

SRC_URI[mips.md5sum] = "3c42df14db9d12041802f4c8fec88e17"
SRC_URI[mips.sha256sum] = "738896d2682211d2079eeaa1c7b8bdd0fe75eb90cd12dff2fc5aeb3cc02562bc"
SRC_URI[arm.md5sum] = "bda1c09ed92a805cedc6770c0dd40e81"
SRC_URI[arm.sha256sum] = "67a3ac98727595a399d5c399d3b66a7fadbe8136ac517e08decba5ea6964674a"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI += "http://downloads.openpli.org/archive/zgemma/linux-${PV}-${ARCH}.tar.gz;name=${ARCH} \
	file://defconfig \
	file://0005-xbox-one-tuner-4.10.patch \
	file://0006-dvb-media-tda18250-support-for-new-silicon-tuner.patch \
	file://make-yyloc-declaration-extern-4.10.12.patch \
	file://fix-build-with-binutils-2.41-kernel41012.patch \
	file://noforce_correct_pointer_usage.patch \
	"

SRC_URI:append:mipsel = " \
	file://0001-add-dmx-source-timecode.patch \
	file://0002-nand-ecc-strength-and-bitflip.patch \
	file://sdio-pinmux.patch \
	file://fix-never-be-null_outside-array-bounds-gcc-12.patch \
	"

SRC_URI:append:arm = " \
	file://export_pmpoweroffprepare.patch \
	file://findkerneldevice.sh \
	file://reserve_dvb_adapter_0.patch \
	file://blacklist_mmc0.patch \
	file://initramfs-subdirboot.cpio.gz;unpack=0 \
	"

S = "${WORKDIR}/linux-${PV}"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "tmp"

# MIPSEL
KERNEL_IMAGETYPE:mipsel = "vmlinux.gz"
KERNEL_OUTPUT:mipsel = "vmlinux.gz"
KERNEL_OUTPUT_DIR:mipsel = "."
KERNEL_CONSOLE:mipsel = "null"
SERIAL_CONSOLE:mipsel ?= ""
KERNEL_EXTRA_ARGS:mipsel = "EXTRA_CFLAGS=-Wno-attribute-alias"

pkg_postinst:kernel-image:mipsel() {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ] ; then
			flash_eraseall /dev/mtd1
			nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
		fi
	fi
	true
}

# ARM
KERNEL_IMAGETYPE:arm = "zImage"
KERNEL_OUTPUT:arm = "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"

FILES:${KERNEL_PACKAGE_NAME}-image:arm = "/${KERNEL_IMAGEDEST}/findkerneldevice.sh"

kernel_do_configure:prepend:arm() {
	install -d ${B}/usr
	install -m 0644 ${WORKDIR}/initramfs-subdirboot.cpio.gz ${B}/
}

kernel_do_install:append:arm() {
        install -d ${D}/${KERNEL_IMAGEDEST}
        install -m 0755 ${WORKDIR}/findkerneldevice.sh ${D}/${KERNEL_IMAGEDEST}
}

pkg_postinst:kernel-image:arm() {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ] ; then
			/${KERNEL_IMAGEDEST}/./findkerneldevice.sh
			dd if=/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} of=/dev/kernel
		fi
	fi
    true
}
