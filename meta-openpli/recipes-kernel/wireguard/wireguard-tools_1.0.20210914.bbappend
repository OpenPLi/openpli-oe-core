FILESEXTRAPATHS:prepend := "${THISDIR}/wireguard-tools:"

SRC_URI += " file://wg-quick.patch;striplevel=2 file://wg"

inherit update-rc.d

INITSCRIPT_NAME = "wg"
INITSCRIPT_PARAMS = "defaults"

do_install:append() {
        install -d ${D}${sysconfdir}/init.d
        install -m 755 ${WORKDIR}/wg ${D}${sysconfdir}/init.d/wg
}
