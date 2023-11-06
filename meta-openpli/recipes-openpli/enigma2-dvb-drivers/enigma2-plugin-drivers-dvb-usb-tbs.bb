DESCRIPTION = "USB DVB driver for TBS Tuners"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
	kernel-module-dvb-usb-tbsusb \
	kernel-module-stv090x \
	kernel-module-stb6100 \
	kernel-module-cx24116 \
	kernel-module-tda10071 \
	kernel-module-stv0299 \
	kernel-module-stv0288 \
	kernel-module-stb6000 \
	firmware-dvb-usb-tbs5925 \
	firmware-dvb-usb-tbs5980 \
	"

PV = "1.1"
