require linux-firmware.inc

DESCRIPTION = "Firmware for RTL8188EU"

SRCREV = "6dda26f406b6eb85c5a364a85a83a73f217395ad"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8188eufw.bin ${D}/${base_libdir}/firmware/rtlwifi/
}
