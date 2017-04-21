require linux-firmware.inc

DESCRIPTION = "Firmware for xc5000"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-fe-xc5000-1.1.fw ${D}${base_libdir}/firmware
}
