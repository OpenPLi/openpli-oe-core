require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-fe-af9013"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-fe-af9013.fw ${D}${base_libdir}/firmware
}
