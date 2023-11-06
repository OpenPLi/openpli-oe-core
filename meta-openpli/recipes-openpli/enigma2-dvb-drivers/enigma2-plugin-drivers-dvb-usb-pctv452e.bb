DESCRIPTION = "USB DVB driver for pctv452e chipsets"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
	kernel-module-dvb-usb-pctv452e \
	"

PV = "1.1"
