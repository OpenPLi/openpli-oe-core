SUMMARY = "Ralink 8812AU/8821AU v4.3.14"
HOMEPAGE = "http://www.realtek.com.tw"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://ifcfg-wlan0;md5=6061d24ec65e191716f64bb3fe580790"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
          file://rtl8812AU-driver-4.3.14.zip \
          file://rtl8812au-gcc5.patch \
          file://rtl8812au-kernel-3.14.patch \
          file://rtl8812au-kernel-4.8.patch \
          file://rtl8812au-kernel-4.11.patch \
          file://rtl8812au-kernel-4.11.9.patch \
          file://rtl8812au-kernel-4.12.patch \
          file://rtl8812au-kernel-4.15.patch \
          file://rtl8812au-kernel-4.20.patch \
          file://rtl8812au-kernel-5.0.patch \
          file://rtl8812au-kernel-5.1.patch \
          file://rtl8812au-kernel-5.2.patch \
          file://rtl8812au-additional-vendor-ids.patch \
          "

SRC_URI[md5sum] = "0f36c65f154971c3b305a1705f9e500f"
SRC_URI[sha256sum] = "e1ab86b4aca9ee599141d2d23dd5c989e0a6d004c8b87a487b370e80a4aba7e2"

S = "${WORKDIR}/rtl8812AU-driver-4.3.14"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR}"

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
    install -m 0644 ${S}/8812au.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless

}

do_package_qa() {
}
