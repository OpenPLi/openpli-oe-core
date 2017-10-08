require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-fe-cx24117"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-fe-cx24117.fw ${D}${base_libdir}/firmware
}