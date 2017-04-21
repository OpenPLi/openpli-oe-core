require linux-firmware.inc

DESCRIPTION = "Firmware for MT7601U"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 mt7601u.bin ${D}${base_libdir}/firmware
}
