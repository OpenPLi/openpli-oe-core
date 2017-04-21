require linux-firmware.inc

DESCRIPTION = "Firmware for demod m88ds3103"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-demod-m88ds3103.fw ${D}${base_libdir}/firmware
}
