require linux-firmware.inc

DESCRIPTION = "Firmware for SDIO RTL8723BU"

do_install() {
	install -d ${D}${base_libdir}/firmware/rtlwifi
	install -m 0644 rtlwifi/rtl8723bu_ap_wowlan.bin ${D}${base_libdir}/firmware/rtlwifi/
	install -m 0644 rtlwifi/rtl8723bu_nic.bin ${D}/${base_libdir}/firmware/rtlwifi/
	install -m 0644 rtlwifi/rtl8723bu_wowlan.bin ${D}/${base_libdir}/firmware/rtlwifi/
	install -m 0644 rtlwifi/rtl8723bu_bt.bin ${D}/${base_libdir}/firmware/rtlwifi/

	install -d ${D}${base_libdir}/firmware/rtl_bt
	install -m 0644 rtl_bt/rtl8723b_fw.bin ${D}${base_libdir}/firmware/rtl_bt
}
