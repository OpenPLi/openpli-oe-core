DESCRIPTION = "USB DVB driver for it913x chipsets"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
        firmware-dvb-usb-af9035-01 \
        firmware-dvb-usb-af9035-02 \
        firmware-dvb-usb-it913x \
        kernel-module-af9033 \
        kernel-module-dvb-usb-af9035 \
        kernel-module-dvb-usb-it913x \
        kernel-module-it913x \
        kernel-module-module-it913x-fe \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
