require linux-firmware.inc

DESCRIPTION = "Firmware for TBS 5925"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

FW = "dvb-usb-tbsqbox-id5925.fw"

S = "${WORKDIR}"
SRC_URI = "file://${FW}"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${FW} ${D}${base_libdir}/firmware
}
