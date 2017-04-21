require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-dibusb-6.0.0.8"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-dibusb-6.0.0.8.fw ${D}${base_libdir}/firmware
}
