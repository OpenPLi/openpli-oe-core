SUMMARY = "Driver for Realtek 8723BS wireless/bluetooth devices"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=c416860023e780aa96e0616b1cda6a49"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/anthonywong/rtl8723bs.git;protocol=https \
    file://0001-makefile-disable-POWER_SAVING.patch \
    file://rt8723bs-makefile.patch \
    file://rt8723bs-remove-debug.patch \
    file://rt8723bs-gcc5.patch \
    file://rt8723bs-add-4.8-support.patch \
    file://rt8723bs-add-4.11-support.patch \
    file://rt8723bs-add-4.12-support.patch \
    file://0001-add-kernel-4.15-support.patch \
    file://compat.patch \
    file://rt8723bs-add-4.19-support.patch \
    file://rt8723bs-add-4.20-support.patch \
    file://rt8723bs-add-5.0-support.patch \
    file://rt8723bs-add-5.1-support.patch \
    file://rt8723bs-add-5.2-support.patch \
    file://rt8723bs-add-5.6-support.patch \
    file://rt8723bs-add-5.8-support.patch \
    file://add-5.15-support.patch \
    "


S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/staging/rtl8723bs
    install -m 0644 ${S}/r8723bs.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/staging/rtl8723bs
}

python do_package_prepend() {
    d.appendVar('PKGV', '-')
    d.appendVar('PKGV', d.getVar("KERNEL_VERSION", True).split("-")[0])
}
