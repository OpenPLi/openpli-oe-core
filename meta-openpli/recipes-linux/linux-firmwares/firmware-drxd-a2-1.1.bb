require linux-firmware.inc

DESCRIPTION = "Firmware for drxd-a2-1.1"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 drxd-a2-1.1.fw ${D}${base_libdir}/firmware
}
