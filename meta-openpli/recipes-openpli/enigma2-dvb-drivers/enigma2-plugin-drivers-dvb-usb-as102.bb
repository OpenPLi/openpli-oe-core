DESCRIPTION = "USB DVB driver for AS102 chipset"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-as102 \
	firmware-as102-data1-st \
	firmware-as102-data2-st \
	"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"
