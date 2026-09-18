# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	${@bb.utils.contains("MACHINE_FEATURES", "dvbproxy", "vuplus-dvb-proxy-${MACHINE} libgles-${MACHINE} vuplus-platform-util-${MACHINE}", "vuplus-dvb-modules-${MACHINE}", d)} \
	gstreamer1.0-libav \
"

KERNEL_MODULE_AUTOLOAD += "xfs"

MACHINE_EXTRA_RRECOMMENDS = " \
	vuplus-shutdown \
	gst-plugin-dvbmediasink \
	ntfs-3g \
"

MACHINE_EXTRA_RDEPENDS += "\
"

EXTRA_IMAGEDEPENDS += "\
	enigma2-plugin-systemplugins-remotecontrolcode \
	enigma2-plugin-extensions-addstreamurl \
	vuplus-tuner-turbo \
	vuplus-tuner-turbo2 \
	hmp-usb-dvb-t2-c-arm \
	${@bb.utils.contains("MACHINE_FEATURES", "bluetooth", "enigma2-plugin-systemplugins-bluetoothsetup", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "bcmwifi", "vuplus-wifi-util-${MACHINE}", "", d)} \
"

TARGET_ARCH = "arm"
DEFAULTTUNE = "armv7ahf-neon"

DVBMEDIASINK_CONFIG = "--with-wmv --with-pcm --with-eac3 --with-h265 --with-dtsdownmix"


PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"

PREFERRED_PROVIDER_virtual/blindscan-dvbc = "vuplus-blindscan-utils-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "vuplus-blindscan-utils-${MACHINE}"

PREFERRED_PROVIDER_virtual/egl = "libgles-${MACHINE}"
PREFERRED_PROVIDER_virtual/libgles2 = "libgles-${MACHINE}"
PREFERRED_PROVIDER_virtual/kodi = "stb-kodi-${MACHINE}"

IMAGE_FSTYPES ?= "tar.bz2"

MACHINE_FEATURES += "alsa usbhost wlan 3dtv switchoff osdposition hdmicec vuplus-cortexa15"
OPENPLI_FEATURES += "kodi"

require conf/machine/include/arm/arch-armv7a.inc
