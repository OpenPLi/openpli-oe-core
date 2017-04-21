require linux-firmware.inc

DESCRIPTION = "Firmware for xc5000c"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-fe-xc5000c-4.1.30.7.fw ${D}${base_libdir}/firmware
}
