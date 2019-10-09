SUMMARY = "Driver for Realtek 8723BS wireless/bluetooth devices"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=c416860023e780aa96e0616b1cda6a49"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
          https://github.com/anthonywong/rtl8723bs/archive/v4.4.1.tar.gz \
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
          "

SRC_URI[md5sum] = "6003f12a873946bc56f495391705e729"
SRC_URI[sha256sum] = "6a66855c3aec845e531e77efca06364b3bbc4d052eb527a002f8c801c9106b40"

S = "${WORKDIR}/rtl8723bs-${PV}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/staging/r8723bs
    install -m 0644 ${S}/r8723bs.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/staging/r8723bs
}

python do_package_prepend() {
    d.appendVar('PKGV', '-')
    d.appendVar('PKGV', d.getVar("KERNEL_VERSION", True).split("-")[0])
}
