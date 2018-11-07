DESCRIPTION = "Driver for Ralink mt7610u USB 802.11a/b/g/n/ac WiFi sticks"
HOMEPAGE = "www.mediatek.com"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://iwpriv_usage.txt;md5=8876ae2c103446a442658f1cc2a01b76"

inherit module

SRC_URI = " \
          git://github.com/rebrane/mt7610u.git \
          file://0001-missing-defines.patch \
          "

SRCREV = "bff379ecc2772dd90bc59401f0e062f309c72895"

SRC_URI[md5sum] = "c46966f5356f60cff8e78bfad38bc3d8"
SRC_URI[sha256sum] = "00c56def1267b0388b3d34352a4105ea052c69e4b511ed746b7da6ac6f96cc6d"

FILES_${PN} += "${sysconfdir}/Wireless/mt7610uSTA/mt7610uSTACard.dat ${sysconfdir}/Wireless/mt7610uSTA/mt7610uSTA.dat ${sysconfdir}/Wireless/mt7610uSTA/SingleSKU.dat"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
    install -d ${D}${sysconfdir}/Wireless/mt7610uSTA
    install -m 0644 ${S}/os/linux/mt7650u_sta.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/mt7650u_sta.ko
    install -m 0644 ${S}/RT2870STA.dat ${D}${sysconfdir}/Wireless/mt7610uSTA/mt7610uSTA.dat
    install -m 0644 ${S}/RT2870STACard.dat ${D}${sysconfdir}/Wireless/mt7610uSTA/mt7610uSTACard.dat
    install -m 0644 ${S}/conf/SingleSKU.dat ${D}${sysconfdir}/Wireless/mt7610uSTA/SingleSKU.dat
}



