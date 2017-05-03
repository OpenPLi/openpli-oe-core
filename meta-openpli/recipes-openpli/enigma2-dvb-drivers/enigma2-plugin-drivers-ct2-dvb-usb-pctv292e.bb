DESCRIPTION = "USB DVB driver for pctv292e chipsets"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	firmware-dvb-demod-si2168-b40 \
	kernel-module-em28xx \
	kernel-module-em28xx-dvb \
	kernel-module-em28xx-rc \
	kernel-module-si2157 \
	kernel-module-si2168 \
	"

PV = "1.0"
