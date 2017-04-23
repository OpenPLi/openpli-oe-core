DESCRIPTION = "USB DVB driver for Technisat Skystar HD"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	blindscan-s2 \
	firmware-dvb-usb-technisat-skystar \
	kernel-module-dvb-usb-technisat-usb2 \
	kernel-module-stv090x \
	kernel-module-stv6110x \
	"

PV = "1.0"

ALLOW_EMPTY_${PN} = "1"
