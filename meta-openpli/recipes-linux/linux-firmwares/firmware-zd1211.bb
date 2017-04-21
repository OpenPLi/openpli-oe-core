require linux-firmware.inc

DESCRIPTION = "Firmware for ZD1211"

do_install() {
	install -d ${D}${base_libdir}/firmware/zd1211
	install -m 0644 zd1211/* ${D}/${base_libdir}/firmware/zd1211/
}
