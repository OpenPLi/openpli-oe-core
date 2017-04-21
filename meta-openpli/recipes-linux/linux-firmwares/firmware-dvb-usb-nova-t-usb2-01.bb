require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-nova-t-usb2-01.fw"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-nova-t-usb2-01.fw ${D}${base_libdir}/firmware
}
