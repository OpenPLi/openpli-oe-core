require linux-firmware.inc

DESCRIPTION = "Firmware for Ralink RT2870"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 rt2870.bin ${D}${base_libdir}/firmware
}
