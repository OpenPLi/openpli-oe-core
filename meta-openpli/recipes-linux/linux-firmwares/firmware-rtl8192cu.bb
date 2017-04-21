require linux-firmware.inc

DESCRIPTION = "Firmware for RTL8192CU"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8192cufw.bin ${D}/${base_libdir}/firmware/rtlwifi/
}
