require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-dibusb-5.0.0.11"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-dibusb-5.0.0.11.fw ${D}${base_libdir}/firmware
}
