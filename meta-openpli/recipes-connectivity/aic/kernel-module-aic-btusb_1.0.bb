SUMMARY = "AIC8800 USB BT driver"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://Makefile;md5=c5a5e471a3ef95c4dc66c9862be9efb4"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://aic_btusb.zip \
    "

S = "${WORKDIR}"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR} KSRC=${STAGING_KERNEL_DIR}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth
    install -m 0644 ${S}/aic_btusb.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth
}

