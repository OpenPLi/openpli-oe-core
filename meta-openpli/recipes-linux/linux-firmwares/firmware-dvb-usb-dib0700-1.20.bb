require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-dib0700-1.20"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-dib0700-1.20.fw ${D}${base_libdir}/firmware
}
