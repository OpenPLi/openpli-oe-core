SUMMARY = "USB DVB driver for Realtek RTL2832 chipset"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-rtl2832 \
	kernel-module-dvb-usb-rtl28xxu \
	kernel-module-e4000 \
	kernel-module-fc0012 \
	kernel-module-fc0013 \
	kernel-module-mt2266 \
	kernel-module-r820t \
	kernel-module-rtl2832 \
	firmware-dvb-usb-af9015 \
	firmware-dvb-usb-af9035-01 \
	firmware-dvb-usb-af9035-02 \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
