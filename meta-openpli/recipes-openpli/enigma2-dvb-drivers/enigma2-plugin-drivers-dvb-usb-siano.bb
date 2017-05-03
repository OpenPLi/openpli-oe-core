DESCRIPTION = "USB DVB driver for Siano chipset"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	firmware-dvb-nova-12mhz-b0 \
	firmware-dvb-siano \
	firmware-isdbt-nova-12mhz-b0 \
	kernel-module-smsdvb \
	kernel-module-smsmdtv \
	kernel-module-smsusb \
	"

PV = "1.1"
