require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-ec168"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-ec168.fw ${D}${base_libdir}/firmware
}
