DESCRIPTION = "USB DVB driver for dib0700 chipset"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
	firmware-dvb-usb-dib0700-1.20 \
	firmware-dvb-usb-dibusb-5.0.0.11 \
	firmware-dvb-usb-dibusb-6.0.0.8 \
	firmware-dvb-usb-dibusb-an2235-01 \
	firmware-xc3028-v27 \
	firmware-xc3028l-v36 \
	kernel-module-dib0070 \
	kernel-module-dib0090 \
	kernel-module-dib3000mb \
	kernel-module-dib3000mc \
	kernel-module-dib7000m \
	kernel-module-dib7000p \
	kernel-module-dib8000 \
	kernel-module-dibx000-common \
	kernel-module-dvb-usb \
	kernel-module-dvb-usb-dib0700 \
	kernel-module-dvb-usb-dibusb-common \
	kernel-module-dvb-usb-dibusb-mc \
	kernel-module-fc0013 \
	kernel-module-mt2060 \
	kernel-module-mt2266 \
	kernel-module-tuner-xc2028 \
	"

PV = "1.1"
