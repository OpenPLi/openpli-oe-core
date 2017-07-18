SUMMARY = "Driver for Realtek USB wireless device 8192eu"
HOMEPAGE = "http://www.realtek.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=335314e18e046a490d9c576b3e03518d"

SRC_URI = "git://github.com/Mange/rtl8192eu-linux-driver.git \
    file://rtl8192eu-makefile.patch \
    file://rtl8192eu-autoconf.patch \
    file://rtl8192eu-gcc5.patch \
    "

S = "${WORKDIR}/git"

inherit module siteinfo

EXTRA_OEMAKE = "CONFIG_RTL8192EU=m"

do_configure() {
        sed -e "s/^CONFIG_PLATFORM_I386_PC.*=.*y/EXTRA_CFLAGS += -Wno-date-time -DCONFIG_${@base_conditional('SITEINFO_ENDIANNESS', 'le', 'LITTLE', 'BIG', d)}_ENDIAN/" -i Makefile
}
do_compile() {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
        oe_runmake -C "${STAGING_KERNEL_DIR}" M="${S}" modules
}

do_install() {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
        install -m 0644 ${S}/8192eu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

