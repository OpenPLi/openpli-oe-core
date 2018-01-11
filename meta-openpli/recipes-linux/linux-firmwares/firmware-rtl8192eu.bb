require linux-firmware.inc

DESCRIPTION = "Firmware for RTL8192EU"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8192eu_nic.bin ${D}/${base_libdir}/firmware/rtlwifi/
}
