require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-it9135"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-it9135.fw ${D}${base_libdir}/firmware
}
