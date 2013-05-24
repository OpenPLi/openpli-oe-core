DESCRIPTION = "USB DVB driver for af9035 devices"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-af9035 \
	firmware-dvb-usb-af9035-02 \
	firmware-dvb-usb-it913x \
	"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"
