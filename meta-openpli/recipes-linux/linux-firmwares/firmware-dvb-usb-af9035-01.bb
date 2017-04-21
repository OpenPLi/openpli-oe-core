require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-af9035-01"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-af9035-01.fw ${D}${base_libdir}/firmware
}
