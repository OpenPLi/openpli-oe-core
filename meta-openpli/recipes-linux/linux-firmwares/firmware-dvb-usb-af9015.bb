require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-af9015"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-af9015.fw ${D}${base_libdir}/firmware
}
