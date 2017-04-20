DESCRIPTION = "USB DVB driver for pctv292e chipsets"

require conf/license/license-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	firmware-dvb-demod-si2168-b40 \
	kernel-module-em28xx \
	kernel-module-em28xx-dvb \
	kernel-module-em28xx-rc \
	kernel-module-si2157 \
	kernel-module-si2168 \
	"

PV = "1.0"

ALLOW_EMPTY_${PN} = "1"
