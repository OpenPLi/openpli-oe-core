DESCRIPTION = "USB DVB driver for Xbox One Tuner"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	kernel-module-mn88472 \
	kernel-module-tda18250 \
	kernel-module-dvb-usb-dib0700 \
	firmware-mn8847x \
	firmware-dvb-demod-mn88472-02 \
	firmware-dvb-usb-dib0700-1.20 \
	"

PV = "1.1"
