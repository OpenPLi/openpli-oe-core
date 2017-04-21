require linux-firmware.inc

DESCRIPTION = "Firmware for RTL8712U"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8712u.bin ${D}/${base_libdir}/firmware/rtlwifi/
}
