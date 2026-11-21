DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

COMPATIBLE_MACHINE = "xpeedc"

KERNEL_RELEASE = "4.1.21"
SRCDATE = "20160408"

SRC_URI[md5sum] = "e7ba35d427bfa40d78cd6e23db7872a2"
SRC_URI[sha256sum] = "88f648e462e9d37c6ed9401b33ee1dd08495e9f66b9c653aefd9fd0a4f5afb26"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

MACHINE_KERNEL_PR:append = ".8"

SRC_URI += "http://downloads.openpli.org/archive/xpeedc/linux-${PV}.tar.xz \
	file://kernel-add-support-for-gcc13.patch \
	file://0002-log2-give-up-on-gcc-constant-optimizations.patch \
	file://0003-dont-mark-register-as-const.patch \
	file://defconfig \
	file://fix-never-be-null_outside-array-bounds-gcc-12.patch \
	file://fix-build-with-binutils-2.41.patch \
	"

inherit kernel machine_kernel_pr

S = "${WORKDIR}/linux-${PV}"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "tmp"

KERNEL_EXTRA_ARGS = "EXTRA_CFLAGS=-Wno-attribute-alias"

FILES:${KERNEL_PACKAGE_NAME}-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

kernel_do_install:append() {
	${STRIP} ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
	gzip -9c ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} > ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	rm ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
}

pkg_postinst:kernel-image () {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz ] ; then
			flash_eraseall /dev/mtd1
			nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
			rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
		fi
	fi
	true
}
