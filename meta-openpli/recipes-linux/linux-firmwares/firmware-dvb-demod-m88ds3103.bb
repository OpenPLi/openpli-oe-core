require linux-firmware.inc

DESCRIPTION = "Firmware for demod m88ds3103"

SRCREV = "b8cd06c25767e5247c9994ee03683dd336405465"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-demod-m88ds3103.fw ${D}${base_libdir}/firmware
}