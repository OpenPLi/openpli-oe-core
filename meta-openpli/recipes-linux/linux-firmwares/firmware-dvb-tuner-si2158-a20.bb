require linux-firmware.inc

DESCRIPTION = "Firmware for tuner si2158 a20-01"

SRCREV = "b8cd06c25767e5247c9994ee03683dd336405465"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-tuner-si2158-a20-01.fw ${D}${base_libdir}/firmware
}
