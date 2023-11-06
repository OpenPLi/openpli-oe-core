DESCRIPTION = "USB DVB driver for dtt200u chipsets"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
	firmware-dvb-usb-dtt200u-01 \
	firmware-dvb-usb-wt220u-02 \
	firmware-dvb-usb-wt220u-fc03 \
	firmware-dvb-usb-wt220u-miglia-01 \
	firmware-dvb-usb-wt220u-zl0353-01 \
	kernel-module-dvb-usb-dtt200u \
	"

PV = "1.1"
