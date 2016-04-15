DESCRIPTION = "USB ATSC driver for Hauppauge WinTV-HVR Tuners"

require conf/license/openpli-gplv2.inc

inherit allarch

RRECOMMENDS_${PN} = " \
	kernel-module-au0828 \
	kernel-module-au8522 \
	kernel-module-au8522-common \
	kernel-module-au8522-decoder \
	kernel-module-au8522-dig \
	kernel-module-cx231xx \
	kernel-module-em28xx \
	kernel-module-tda18271 \
	kernel-module-tveeprom \
	kernel-module-xc5000 \
	firmware-xc5000 \
	"

PV = "1.0"

ALLOW_EMPTY_${PN} = "1"
