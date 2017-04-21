require linux-firmware.inc

DESCRIPTION = "Firmware for isdbt_nova_12mhz_b0.inp"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 isdbt_nova_12mhz_b0.inp ${D}${base_libdir}/firmware
}
