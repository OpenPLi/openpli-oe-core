require linux-firmware.inc

DESCRIPTION = "Firmware for TDA10071 dvb frontend"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-fe-tda10071.fw ${D}${base_libdir}/firmware
}
