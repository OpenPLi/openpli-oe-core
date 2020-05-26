DESCRIPTION = "USB DVB driver for Xbox One Tuner"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
        kernel-module-dvb-usb-dib0700 \
        kernel-module-mn88472 \
        firmware-dvb-demod-mn88472-02 \
        firmware-dvb-usb-dib0700-1.20 \
	"

PV = "1.0"
