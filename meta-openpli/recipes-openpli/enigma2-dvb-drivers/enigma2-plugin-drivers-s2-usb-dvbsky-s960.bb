DESCRIPTION = "USB dvbsky driver for S960"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-dvbsky \
	kernel-module-ts2020 \
	firmware-dvb-demod-m88ds3103 \
	"

PV = "1.0"

ALLOW_EMPTY_${PN} = "1"