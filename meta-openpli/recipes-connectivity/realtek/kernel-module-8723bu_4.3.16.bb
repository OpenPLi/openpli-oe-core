DESCRIPTION = "Driver for Realtek USB wireless devices"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=7c7799e38fb24c3c8a114bac8e2517de"

# backward compatibility
RPROVIDES_${PN} = "rtl8723bu"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
          git://github.com/lwfinger/rtl8723bu.git;protocol=https;branch=master \
          file://rt8723bu-makefile.patch \
          file://rt8723bu-gcc5.patch \
          "

S = "${WORKDIR}/git"

MACHINE_KERNEL_PR_append = ".0"

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/8723bu.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

