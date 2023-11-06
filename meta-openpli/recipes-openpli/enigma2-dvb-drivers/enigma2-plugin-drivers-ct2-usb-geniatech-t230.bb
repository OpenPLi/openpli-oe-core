DESCRIPTION = "USB geniatech driver for T230"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
	kernel-module-dvb-usb-cxusb \
	kernel-module-si2157 \
	kernel-module-si2168 \
	firmware-dvb-demod-si2168-b40 \
	"

PV = "1.0"
