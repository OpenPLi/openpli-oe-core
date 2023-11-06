DESCRIPTION = "USB DVB driver for Technisat Skystar HD"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
	blindscan-s2 \
	firmware-dvb-usb-technisat-skystar \
	kernel-module-dvb-usb-technisat-usb2 \
	kernel-module-stv090x \
	kernel-module-stv6110x \
	"

PV = "1.0"
