SUMMARY = "AIC8800 USB WiFi driver"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://md5.c;md5=6b72908aecd98fee9e4883696905a46f"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} += "firmware-aic8800d80"

SRC_URI = " \
    file://aic_load_fw.zip \
    "

S = "${WORKDIR}"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR} KSRC=${STAGING_KERNEL_DIR}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/aic8800
    install -m 0644 ${S}/aic_load_fw.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/aic8800
}

