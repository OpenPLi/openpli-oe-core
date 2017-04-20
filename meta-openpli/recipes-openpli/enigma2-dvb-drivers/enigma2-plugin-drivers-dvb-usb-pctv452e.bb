DESCRIPTION = "USB DVB driver for pctv452e chipsets"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-pctv452e \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
