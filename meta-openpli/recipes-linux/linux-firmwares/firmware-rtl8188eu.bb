require linux-firmware.inc

DESCRIPTION = "Firmware for RTL8188EU"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8188eufw.bin ${D}/${base_libdir}/firmware/rtlwifi/
}
