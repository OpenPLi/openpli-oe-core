SUMMARY = "Driver for Realtek USB wireless device 8192fu"
HOMEPAGE = "http://www.realtek.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://wlan0dhcp;md5=069fc07a0c587af26235837dc342eb25"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://rtl8192FU_rtl8725AU_WiFi_linux_v5.8.6.4_37082.20200716_COEX20190910-0d02.zip \
    file://0001-makefile.patch \
    file://0002-fix-for-4.19.patch \
    file://0003-fix-for-5.1.patch \
    file://0004-fix-for-5.6.patch \
    file://0005-fix-for-5.8.patch \
    file://0001-add-linux-5.12-support.patch \
    file://0003-fix-multiple-definitions.patch \
    file://0006-fix-for-5.15.patch \
    file://rtl8192fu-complement-to-5.15.patch \
    "

S = "${WORKDIR}"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR} KSRC=${STAGING_KERNEL_DIR}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
    install -m 0644 ${S}/8192fu.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

do_package_qa() {
}
