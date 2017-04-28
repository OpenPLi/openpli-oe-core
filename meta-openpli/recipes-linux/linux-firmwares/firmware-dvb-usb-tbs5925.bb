require linux-firmware-local.inc

DESCRIPTION = "Firmware for TBS 5925"
S = "${WORKDIR}"

FW = "dvb-usb-tbsqbox-id5925.fw"
SRC_URI = "file://${FW}"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${FW} ${D}${base_libdir}/firmware
}
