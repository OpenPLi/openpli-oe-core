require linux-firmware.inc

DESCRIPTION = "Firmware for DVB USB TeVii S660 adapter"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-s660.fw ${D}${base_libdir}/firmware
}
