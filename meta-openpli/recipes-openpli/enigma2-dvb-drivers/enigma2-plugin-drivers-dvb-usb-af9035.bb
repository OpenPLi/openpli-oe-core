DESCRIPTION = "USB DVB driver for af9035 devices"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RRECOMMENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-af9035 \
	${DVBPROVIDER}-module-af9033 \
	${DVBPROVIDER}-module-tua9001 \
	${DVBPROVIDER}-module-mxl5007t \
	${DVBPROVIDER}-module-tda18218 \
	firmware-dvb-usb-af9035-01 \
	firmware-dvb-usb-af9035-02 \
	firmware-dvb-usb-it913x \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
