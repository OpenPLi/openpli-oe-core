require linux-firmware.inc

DESCRIPTION = "Firmware for HTC9271"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 htc_9271.fw ${D}/${base_libdir}/firmware/
}
