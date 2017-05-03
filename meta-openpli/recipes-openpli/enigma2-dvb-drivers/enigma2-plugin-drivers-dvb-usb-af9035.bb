DESCRIPTION = "USB DVB driver for af9035 devices"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	firmware-dvb-usb-af9035-01 \
	firmware-dvb-usb-af9035-02 \
	firmware-dvb-usb-it913x \
	kernel-module-af9033 \
	kernel-module-dvb-usb-af9035 \
	kernel-module-it913x \
	kernel-module-mxl5007t \
	kernel-module-tda18218 \
	kernel-module-tua9001 \
	"

PV = "1.3"
