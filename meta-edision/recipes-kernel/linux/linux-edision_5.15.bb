require linux-os.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "coreutils-native"

SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v5.x/linux-${PV}.tar.xz;name=kernel \
	http://downloads.openpli.org/archive/edision/edision-kernel-${PV}.patch.xz;apply=yes;name=kernelpatch \
	file://0001-ata-ahci-fix-enum-constants-for-gcc-13.patch \
	file://defconfig \
	file://findkerneldevice.py \
	"

COMPATIBLE_MACHINE = "osmini4k|osmio4k|osmio4kplus"

SRC_URI[kernel.md5sum] = "071d49ff4e020d58c04f9f3f76d3b594"
SRC_URI[kernel.sha256sum] = "57b2cf6991910e3b67a1b3490022e8a0674b6965c74c12da1e99d138d1991ee8"
SRC_URI[kernelpatch.md5sum] = "a216bc0580f69c604f0b1e6f9d1a261f"
SRC_URI[kernelpatch.sha256sum] = "4b9ea4d6217769b91a270a917fa082d6f090d2d4aabb701d5b0ed9936309bdf3"

FILES:${KERNEL_PACKAGE_NAME}-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}* ${KERNEL_IMAGEDEST}/findkerneldevice.py"

do_compile_kernelmodules:append() {
	# openembedded-core 0fc66a0b64953aae38d0124b57615fffaec8de52
	if (grep -q -i -e '^CONFIG_MODULES=y$' ${B}/.config); then
		# 5.10+ kernels have module.lds that we need to copy for external module builds
		if [ -e "${B}/scripts/module.lds" ]; then
			install -Dm 0644 ${B}/scripts/module.lds ${STAGING_KERNEL_BUILDDIR}/scripts/module.lds
		fi
	fi
}

kernel_do_install:append () {
	install -d ${D}/${KERNEL_IMAGEDEST}
	install -m 0644 ${KERNEL_OUTPUT_DIR}/${KERNEL_IMAGETYPE} ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
	install -m 0644 ${WORKDIR}/findkerneldevice.py ${D}/${KERNEL_IMAGEDEST}
}

pkg_postinst:kernel-image () {
	if [ -z "$D" ]
	then
		python3 /${KERNEL_IMAGEDEST}/findkerneldevice.py
		dd if=/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} of=/dev/kernel
	fi
}
