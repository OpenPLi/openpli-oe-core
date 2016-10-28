require linux-firmware.inc

DESCRIPTION = "Firmware for MT7601U"

SRCREV = "6dda26f406b6eb85c5a364a85a83a73f217395ad"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 mt7601u.bin ${D}${base_libdir}/firmware
}
