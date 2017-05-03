DESCRIPTION = "USB dvbsky driver for T330"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-dvbsky \
	kernel-module-si2157 \
	kernel-module-si2168 \
	firmware-dvb-demod-si2168-b40 \
	"

PV = "1.0"
