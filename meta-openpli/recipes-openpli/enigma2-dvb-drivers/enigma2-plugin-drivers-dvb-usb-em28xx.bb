DESCRIPTION = "USB DVB driver for EM28xx chipset"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	kernel-module-cxd2820r \
	kernel-module-em28xx-dvb \
	kernel-module-tda10071 \
	firmware-dvb-fe-tda10071 \
	"

PV = "1.1"
