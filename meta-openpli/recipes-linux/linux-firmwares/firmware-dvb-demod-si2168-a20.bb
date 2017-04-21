require linux-firmware.inc

DESCRIPTION = "Firmware for demod si2168 a20-01"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-demod-si2168-a20-01.fw ${D}${base_libdir}/firmware
}
