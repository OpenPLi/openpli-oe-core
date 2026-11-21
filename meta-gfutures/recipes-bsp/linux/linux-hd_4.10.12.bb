DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

KERNEL_RELEASE = "4.10.12"
COMPATIBLE_MACHINE = "^hd11$|^hd1100$|^hd1200$|^hd1265$|^hd1500$|^hd2400$|^hd500c$|^ĥd51$|^hd530$|^vs1000$|^vs1500$|^bre2ze4k$"

inherit kernel machine_kernel_pr

MACHINE_KERNEL_PR:append = ".3"

SRC_URI[mips.md5sum] = "3c42df14db9d12041802f4c8fec88e17"
SRC_URI[mips.sha256sum] = "738896d2682211d2079eeaa1c7b8bdd0fe75eb90cd12dff2fc5aeb3cc02562bc"
SRC_URI[arm.md5sum] = "bda1c09ed92a805cedc6770c0dd40e81"
SRC_URI[arm.sha256sum] = "67a3ac98727595a399d5c399d3b66a7fadbe8136ac517e08decba5ea6964674a"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI += "http://downloads.openpli.org/archive/gfutures/linux-${PV}-${ARCH}.tar.gz;name=${ARCH} \
	file://defconfig \
	file://export_pmpoweroffprepare.patch \
	file://make-yyloc-declaration-extern.patch \
	file://fix-never-be-null_outside-array-bounds-gcc-12.patch \
	file://fix-build-with-binutils-2.41.patch \
	file://noforce_correct_pointer_usage.patch \
"
SRC_URI:append:arm = " \
	file://findkerneldevice.sh \
	file://initramfs-subdirboot.cpio.gz;unpack=0 \
	file://reserve_dvb_adapter_0.patch \
	file://blacklist_mmc0.patch \
	file://enable_hauppauge_solohd.patch \
"

S = "${WORKDIR}/linux-${PV}"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"

# MIPSEL
KERNEL_IMAGETYPE:mipsel = "vmlinux.gz"
KERNEL_OUTPUT_DIR:mipsel = "."
KERNEL_IMAGEDEST:mipsel = "tmp"
KERNEL_CONSOLE:mipsel = "null"
SERIAL_CONSOLE:mipsel ?= ""

KERNEL_EXTRA_ARGS:mipsel = "EXTRA_CFLAGS=-Wno-attribute-alias"

# Replaced by kernel_output_dir
KERNEL_OUTPUT:mipsel = "vmlinux.gz"

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
KERNEL_OUTPUT:arm = "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"
KERNEL_IMAGETYPE:arm = "zImage"
KERNEL_IMAGEDEST:arm = "tmp"

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
