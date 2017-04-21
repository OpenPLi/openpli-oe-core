require linux-firmware.inc

DESCRIPTION = "Firmware for tuner si2158 a20-01"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-tuner-si2158-a20-01.fw ${D}${base_libdir}/firmware
}
