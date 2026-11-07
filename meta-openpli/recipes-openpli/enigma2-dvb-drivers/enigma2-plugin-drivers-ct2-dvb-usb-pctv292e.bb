DESCRIPTION = "USB DVB driver for pctv292e chipsets"

require dvb-usb-drivers-meta.inc

RRECOMMENDS_${PN} = " \
	kernel-module-em28xx \
	kernel-module-em28xx-dvb \
	kernel-module-em28xx-rc \
	kernel-module-si2157 \
	kernel-module-si2168 \
	firmware-dvb-demod-si2168-02 \
	firmware-dvb-demod-si2168-a20 \
	firmware-dvb-demod-si2168-a30 \
	firmware-dvb-demod-si2168-b40 \
	firmware-dvb-demod-si2168-d60 \
	firmware-dvb-tuner-si2141-a10 \
	firmware-dvb-tuner-si2158-a20 \
"

PV = "1.0"
