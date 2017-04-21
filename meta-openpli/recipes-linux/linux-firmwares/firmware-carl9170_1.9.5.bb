DESCRIPTION = "Firmware for CARL9170"
LICENCE = "closed"

require linux-firmware.inc

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ar9170-3.fw ${D}${base_libdir}/firmware/carl9170-1.fw
}
