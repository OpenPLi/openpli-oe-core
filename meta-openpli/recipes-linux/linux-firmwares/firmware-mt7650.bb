require linux-firmware.inc

DESCRIPTION = "Firmware BT for MT7650 "

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 mt7650.bin ${D}${base_libdir}/firmware
}
