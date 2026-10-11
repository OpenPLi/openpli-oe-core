MACHINE_KERNEL_PR = "r0"
KERNEL_MODULE_AUTOLOAD += "xfs"

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

PREFERRED_VERSION_linux-zgemma = "4.4.176"

#Provider
PREFERRED_PROVIDER_virtual/kernel ?= "linux-zgemma"
PREFERRED_PROVIDER_virtual/gstreamer1.0-dvbmediasink ?= "gstreamer1.0-plugin-dvbmediasink"
PREFERRED_PROVIDER_virtual/enigma2-mediaservice ?= "enigma2-plugin-systemplugins-servicehisilicon"

require conf/machine/include/arm/armv7a/tune-cortexa7.inc
require conf/machine/include/zgemma-essential.inc

# E2 OSD settings
EXTRA_OECONF:append:pn-enigma2 = " --with-alphablendingacceleration=always --with-blitaccelerationthreshold=250 --with-fillaccelerationthreshold=190000"

# Qt
PACKAGECONFIG_GL:pn-qtbase = " "
PACKAGECONFIG:append:pn-qtbase = " linuxfb no-opengl"

# Gstreamer dvbmediasink
DVBMEDIASINK_CONFIG = "--with-h265 --with-vb8 --with-wma --with-wmv --with-pcm  --with-eac3 --with-amr --with-spark"

MACHINE_EXTRA_RRECOMMENDS = " \
	ntfs-3g \
"

MACHINE_FEATURES += " alsa usbhost wlan 3dtv switchoff osdposition hdmicec"

IMAGEVERSION := "${DISTRO_NAME}-${DISTRO_VERSION}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"
IMAGEDIR ?= "zgemma"
IMAGEDIRRECOVERY ?= "zgemma-recovery"
IMGDEPLOYDIR ?= "${DEPLOY_DIR_IMAGE}"

require conf/machine/include/zgemma-partitions.inc

IMAGE_CMD:ubifs:prepend = " \
	rm -Rf ${IMAGE_ROOTFS}/boot/*; \
	rm -Rf ${IMAGE_ROOTFS}/tmp/*; \
	"

IMAGE_CMD:ubi:append = " \
	mkdir -p ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}; \
	mkdir -p ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}; \
	cp ${IMGDEPLOYDIR}/${IMAGE_NAME}.ubi ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/rootfs.ubi; \
	cp ${DEPLOY_DIR_IMAGE}/uImage ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/uImage; \
	cp ${IMGDEPLOYDIR}/${IMAGE_NAME}.ubi ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/rootfs.ubi; \
	cp ${DEPLOY_DIR_IMAGE}/uImage ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/uImage; \
	cp ${IMGDEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/bootoptions.yaffs ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/bootoptions.yaffs; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/update_bootargs_${MACHINE}.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/update_bootargs_${MACHINE}.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/fastboot.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/fastboot.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/bootargs.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/bootargs.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/pq_param.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/pqparam.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/baseparam.img ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/baseparam.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/logo.img ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/logo.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/recovery.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/recovery.bin; \
	echo "${IMAGEVERSION}" > ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/imageversion; \
	cd ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}; \
	zip -r ../${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip *; \
	cd ..; \
	rm -Rf ${IMAGEDIRRECOVERY}; \
	cd ${IMGDEPLOYDIR}/${IMAGEDIR}; \
	zip -r ../${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_nand.zip *; \
	cd ..; \
	rm -Rf ${IMAGEDIR}; \
	"
