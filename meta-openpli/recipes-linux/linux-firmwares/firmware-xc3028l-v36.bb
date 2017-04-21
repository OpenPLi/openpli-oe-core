require linux-firmware.inc

DESCRIPTION = "Firmware for xc3028L-v36"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 xc3028L-v36.fw ${D}${base_libdir}/firmware
}
