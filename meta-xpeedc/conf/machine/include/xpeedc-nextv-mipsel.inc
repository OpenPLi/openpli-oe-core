MACHINE_KERNEL_PR = "r0"

require conf/machine/include/xpeedc-nextv-essential.inc

KERNEL_MODULE_AUTOLOAD += "xfs"
IMAGE_FSTYPES += "ubi"
UBI_VOLNAME = "rootfs"

TARGET_ARCH = "mipsel"
DEFAULTTUNE ?= "mips32el"

require conf/machine/include/mips/tune-mips32.inc

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	nextv-dvb-modules-${MACHINE} \
	nextv-blindscan-dvbc-utils-${MACHINE} \
	"

#Provider
PREFERRED_PROVIDER_virtual/kernel = "linux-xpeedc-nextv"
PREFERRED_PROVIDER_virtual/blindscan-dvbc = "nextv-blindscan-dvbc-utils-${MACHINE}"

MACHINE_FEATURES += "alsa usbhost wlan 3dtv switchoff osdposition hdmicec"

MACHINE_EXTRA_RRECOMMENDS = " \
	gst-plugin-dvbmediasink \
	ntfs-3g \
	"

DVBMEDIASINK_CONFIG:mipsel = "--with-wma --with-wmv --with-pcm --with-dts --with-eac3 --with-vb6 --with-vb8 --with-spark \
	${@bb.utils.contains('MACHINE_FEATURES', 'h265', '--with-h265' , '', d)} \
	"

IMAGEVERSION := "${DISTRO_NAME}-${DISTRO_VERSION}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"

IMAGEDIR ?= "${MACHINE}"
IMGDEPLOYDIR ?= "${DEPLOY_DIR_IMAGE}"

# MIPSEL
IMAGE_CMD:ubifs:prepend:mipsel = " \
    rm -Rf ${IMAGE_ROOTFS}/boot/*; \
    rm -Rf ${IMAGE_ROOTFS}/tmp/*; \
    "

IMAGE_CMD:ubi:append:mipsel = " \
	mkdir -p ${IMGDEPLOYDIR}/${IMAGEDIR}; \
	cp ${IMGDEPLOYDIR}/${IMAGE_NAME}.ubi ${IMGDEPLOYDIR}/${IMAGEDIR}/rootfs.bin; \
	gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${IMGDEPLOYDIR}/${IMAGEDIR}/kernel.bin; \
	echo "${IMAGEVERSION}" > ${IMGDEPLOYDIR}/${IMAGEDIR}/imageversion; \
	echo "rename this file to 'force' to force an update without confirmation" > ${IMGDEPLOYDIR}/${IMAGEDIR}/noforce; \
	cd ${IMGDEPLOYDIR}; \
	zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip ${IMAGEDIR}/*; \
	rm -Rf ${IMAGEDIR}; \
	rm -Rf ${IMGDEPLOYDIR}/boot; \
	"
