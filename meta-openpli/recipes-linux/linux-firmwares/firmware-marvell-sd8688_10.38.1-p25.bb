require linux-firmware.inc

DESCRIPTION = "Firmware for Marvell 8688 sdio wifi/bt chipset"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 libertas/sd8688.bin libertas/sd8688_helper.bin ${D}${base_libdir}/firmware
	install -m 0644 LICENCE.libertas ${D}${base_libdir}/firmware/LICENCE_sd8688.txt
}
