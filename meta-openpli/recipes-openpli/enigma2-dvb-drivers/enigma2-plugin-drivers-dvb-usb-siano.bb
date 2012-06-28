DESCRIPTION = "USB DVB driver for Siano chipset"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-smsusb \
	${DVBPROVIDER}-module-smsdvb \
	firmware-dvb-siano \
	"

PV = "1.0"
PR = "r4"

ALLOW_EMPTY_${PN} = "1"
