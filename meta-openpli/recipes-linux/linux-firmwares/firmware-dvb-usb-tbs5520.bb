require linux-firmware.inc

DESCRIPTION = "Firmware for TBS 5520/5520SE"

FW = "dvb-usb-tbsqbox-id5520.fw"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${FW} ${D}${base_libdir}/firmware
}
