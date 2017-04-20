DESCRIPTION = "USB DVB driver for Siano chipset"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	firmware-dvb-nova-12mhz-b0 \
	firmware-dvb-siano \
	firmware-isdbt-nova-12mhz-b0 \
	kernel-module-smsdvb \
	kernel-module-smsmdtv \
	kernel-module-smsusb \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
