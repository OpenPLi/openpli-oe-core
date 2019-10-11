SUMMARY = "Driver for Realtek USB wireless device 8192eu"
HOMEPAGE = "http://www.realtek.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=be8c96073836ae962143b35bebc83551"

# backward compatibility
RPROVIDES_${PN} = "rtl8192eu"

inherit module siteinfo

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
          git://github.com/Mange/rtl8192eu-linux-driver.git;branch=realtek-4.4.x \
          file://rtl8192eu-makefile.patch \
          file://rtl8192eu-gcc5.patch \
          file://CHECKSM_IPV6_H.patch \
          "

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "CONFIG_RTL8192EU=m"

do_configure() {
        sed -e "s/^CONFIG_PLATFORM_I386_PC.*=.*y/EXTRA_CFLAGS += -Wno-date-time -DCONFIG_${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'LITTLE', 'BIG', d)}_ENDIAN/" -i Makefile
}

do_compile() {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
        oe_runmake -C "${STAGING_KERNEL_DIR}" M="${S}" modules 
}

do_install() {
        install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
        install -m 0644 ${S}/8192eu.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}
