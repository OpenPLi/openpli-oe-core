require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-dtt200u-01.fw"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-dtt200u-01.fw ${D}${base_libdir}/firmware
}
