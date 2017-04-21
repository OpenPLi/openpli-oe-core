require linux-firmware.inc

DESCRIPTION = "Firmware for sms1xxx-nova-a-dvbt-01"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 sms1xxx-nova-a-dvbt-01.fw ${D}${base_libdir}/firmware
}
