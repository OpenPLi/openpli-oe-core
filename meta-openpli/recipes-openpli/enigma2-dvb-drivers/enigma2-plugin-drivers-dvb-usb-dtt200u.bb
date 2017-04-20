DESCRIPTION = "USB DVB driver for dtt200u chipsets"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	firmware-dvb-usb-dtt200u-01 \
	firmware-dvb-usb-wt220u-02 \
	firmware-dvb-usb-wt220u-fc03 \
	firmware-dvb-usb-wt220u-miglia-01 \
	firmware-dvb-usb-wt220u-zl0353-01 \
	kernel-module-dvb-usb-dtt200u \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
