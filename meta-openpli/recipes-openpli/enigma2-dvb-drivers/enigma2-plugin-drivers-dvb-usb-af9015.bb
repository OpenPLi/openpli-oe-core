DESCRIPTION = "USB DVB driver for Afatech 9015 chipset"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	firmware-dvb-fe-af9013 \
	firmware-dvb-usb-af9015 \
	kernel-module-af9013 \
	kernel-module-dvb-pll \
	kernel-module-dvb-usb \
	kernel-module-dvb-usb-af9015 \
	kernel-module-mc44s803 \
	kernel-module-mt2060 \
	kernel-module-mxl5005s \
	kernel-module-mxl5007t \
	kernel-module-qt1010 \
	kernel-module-stv0299 \
	kernel-module-tda18218 \
	kernel-module-tda18271 \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
