require linux-firmware.inc

DESCRIPTION = "Firmware for Afatech AF9005"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 af9005.fw ${D}${base_libdir}/firmware
}
