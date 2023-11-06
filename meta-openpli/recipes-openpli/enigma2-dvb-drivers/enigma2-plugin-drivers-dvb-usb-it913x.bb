DESCRIPTION = "USB DVB driver for it913x chipsets"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
        firmware-dvb-usb-af9035-01 \
        firmware-dvb-usb-af9035-02 \
        firmware-dvb-usb-it913x \
        kernel-module-af9033 \
        kernel-module-dvb-usb-af9035 \
        kernel-module-dvb-usb-it913x \
        kernel-module-it913x \
        kernel-module-it913x-fe \
	"

PV = "1.2"
