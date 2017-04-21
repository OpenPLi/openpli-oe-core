require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-tvwalkert"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-tvwalkert.fw ${D}${base_libdir}/firmware
}
