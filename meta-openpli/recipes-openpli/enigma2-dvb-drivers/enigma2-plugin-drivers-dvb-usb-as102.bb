DESCRIPTION = "USB DVB driver for AS102 chipset"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	firmware-as102-data1-st \
	firmware-as102-data2-st \
	kernel-module-as102-fe \
	kernel-module-dvb-as102 \
	"

PV = "1.1"
