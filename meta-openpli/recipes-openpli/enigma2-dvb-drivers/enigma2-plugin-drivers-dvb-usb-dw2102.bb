DESCRIPTION = "USB DVB driver for DW210x/DW310x chipset"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-dvb-usb-dw2102 \
	kernel-module-stb6100 \
	kernel-module-stv090x \
	firmware-dvb-usb-s660 \
	firmware-dvb-fe-ds3000 \
	"

PV = "1.1"

ALLOW_EMPTY_${PN} = "1"
