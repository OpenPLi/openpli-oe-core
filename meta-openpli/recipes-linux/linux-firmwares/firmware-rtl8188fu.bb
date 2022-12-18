require linux-firmware.inc

DESCRIPTION = "Firmware for RTL8188FU"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8188fufw.bin ${D}/${base_libdir}/firmware/rtlwifi/
}
