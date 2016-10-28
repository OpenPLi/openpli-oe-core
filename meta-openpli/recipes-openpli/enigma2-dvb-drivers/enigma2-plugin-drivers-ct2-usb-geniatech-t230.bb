DESCRIPTION = "USB geniatech driver for T230"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-cxusb \
	kernel-module-si2157 \
	kernel-module-si2168 \
	firmware-dvb-demod-si2168-b40 \
	"

PV = "1.0"

ALLOW_EMPTY_${PN} = "1"