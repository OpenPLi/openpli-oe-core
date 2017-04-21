require linux-firmware.inc

DESCRIPTION = "Firmware for sms1xxx-hcw-55xxx-isdbt-02"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 sms1xxx-hcw-55xxx-isdbt-02.fw ${D}${base_libdir}/firmware
}
