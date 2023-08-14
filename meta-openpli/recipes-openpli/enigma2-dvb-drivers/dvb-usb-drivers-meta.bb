DESCRIPTION = "meta file for USB DVB drivers"

PV = "1.2"

require dvb-usb-drivers-meta.inc

# Get the kernel version for this image, we need it to build conditionally on kernel version
# NB: this only works in the feed, as the kernel needs to be build before the headers are available
export KERNEL_VERSION = "${@oe.utils.read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"

OPTIONAL_DVBUSB_PACKAGES = "\
	${@ 'enigma2-plugin-drivers-dvb-usb-as102' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.2') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-dvb-usb-it913x' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.2') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-dvb-usb-pctv452e' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.2') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-dvb-usb-rtl2832' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.4') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-dvb-usb-af9035' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.5') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-atsc-usb-hauppauge' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.7') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-dvb-usb-em28xx' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.7') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-ct2-dvb-usb-pctv292e' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.16') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-s2-usb-dvbsky-s960' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.18') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-ct2-usb-geniatech-t230' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.19') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-ct2-usb-dvbsky-t330' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.19') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-atsc-usb-hauppauge-955q' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.1') > 0) else '' } \
	${@ 'enigma2-plugin-drivers-ct2-usb-xbox-one-tuner' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.16') > 0) else '' } \
	\
	enigma2-plugin-drivers-dvb-usb-tbs \
	"

# optional for the bsp layer when an usb dvb devices is backport

OPTIONAL_EXTRA_DVBUSB_PACKAGES ?= " "

DEPENDS = "\
	enigma2-plugin-drivers-atsc-usb-hauppauge-950q \
	enigma2-plugin-drivers-dvb-usb-af9015 \
	enigma2-plugin-drivers-dvb-usb-dib0700 \
	enigma2-plugin-drivers-dvb-usb-dtt200u \
	enigma2-plugin-drivers-dvb-usb-dw2102 \
	enigma2-plugin-drivers-dvb-usb-siano \
	enigma2-plugin-drivers-dvb-usb-technisat-skystar \
	${OPTIONAL_DVBUSB_PACKAGES} \
	${OPTIONAL_EXTRA_DVBUSB_PACKAGES} \
	"

