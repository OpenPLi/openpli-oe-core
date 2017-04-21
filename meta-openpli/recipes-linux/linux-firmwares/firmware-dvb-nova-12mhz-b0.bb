require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-nova_12Mhz"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb_nova_12mhz_b0.inp ${D}${base_libdir}/firmware
}
