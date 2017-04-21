require linux-firmware.inc

DESCRIPTION = "Firmware for drx397xD.B1"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 drx397xD.B1.fw ${D}${base_libdir}/firmware
}
