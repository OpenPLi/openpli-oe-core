DESCRIPTION = "USB DVB driver for AS102 chipset"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	firmware-as102-data1-st \
	firmware-as102-data2-st \
	kernel-module-as102-fe \
	kernel-module-dvb-as102 \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
