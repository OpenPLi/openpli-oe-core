SUMMARY = "Ralink 8189es v1.0"
HOMEPAGE = "http://www.realtek.com.tw"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://ifcfg-wlan0;md5=a84acae65af4b2d44d5035aa9f63cd85"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
          file://rtl8189es-driver-1.0-20180522.zip \
          file://rtl8189es-kernel-3.2.patch \
          file://rtl8189es-kernel-4.20.patch \
          file://rtl8189es-kernel-5.0.patch \
          file://rtl8189es-kernel-5.1.patch \
          file://rtl8189es-kernel-5.2.patch \
          file://rtl8189es-kernel-5.6.patch \
          file://rtl8189es-kernel-5.8.patch \
          file://rtl8189es-kernel-5.15.patch \
          file://rtl8189es-complement-to-5.15.patch \
          "

SRC_URI[md5sum] = "df5d47702271a7bc429f893d6f044221"
SRC_URI[sha256sum] = "24285fe296281c1da349b9bc267d92fff9a2bc573d04927b51c803b2812846b9"

S = "${WORKDIR}/rtl8189ES_linux"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR} KSRC=${STAGING_KERNEL_DIR}"

do_compile () {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
    oe_runmake 'M={D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless' \
        'KERNEL_SOURCE=${STAGING_KERNEL_DIR}' \
        'LINUX_SRC=${STAGING_KERNEL_DIR}' \
        'KDIR=${STAGING_KERNEL_DIR}' \
        'KERNDIR=${STAGING_KERNEL_DIR}' \
        'KSRC=${STAGING_KERNEL_DIR}' \
        'KERNEL_VERSION=${KERNEL_VERSION}' \
        'KVER=${KERNEL_VERSION}' \
        'CC=${KERNEL_CC}' \
        'AR=${KERNEL_AR}' \
        'LD=${KERNEL_LD}'
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
    install -m 0644 ${S}/8189es.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}
