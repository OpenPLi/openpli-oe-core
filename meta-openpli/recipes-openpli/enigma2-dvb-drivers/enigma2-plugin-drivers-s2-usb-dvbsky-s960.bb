DESCRIPTION = "USB dvbsky driver for S960"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-dvbsky \
	kernel-module-ts2020 \
	firmware-dvb-demod-m88ds3103 \
	"

PV = "1.0"
