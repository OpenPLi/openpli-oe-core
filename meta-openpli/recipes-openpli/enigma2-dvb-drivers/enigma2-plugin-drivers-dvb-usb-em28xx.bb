DESCRIPTION = "USB DVB driver for EM28xx chipset"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-cxd2820r \
	kernel-module-em28xx-dvb \
	kernel-module-tda10071 \
	firmware-dvb-fe-tda10071 \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
