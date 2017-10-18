DESCRIPTION = "USB ATSC driver for Hauppauge WinTV-HVR Tuners"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	kernel-module-au0828 \
	kernel-module-au8522 \
	kernel-module-au8522-common \
	kernel-module-au8522-decoder \
	kernel-module-au8522-dig \
	kernel-module-cx231xx \
	kernel-module-cx231xx-alsa \
	kernel-module-cx231xx-dvb \
	kernel-module-cx2341x \
	kernel-module-em28xx \
	kernel-module-lgdt3305 \
	kernel-module-tda18271 \
	kernel-module-tda18271c2dd \
	kernel-module-tveeprom \
	kernel-module-xc5000 \
	firmware-xc5000 \
	"

PV = "1.1"
