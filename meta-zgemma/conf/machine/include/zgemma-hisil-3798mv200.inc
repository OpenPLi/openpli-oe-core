KERNEL_MODULE_AUTOLOAD += "xfs"

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

PREFERRED_VERSION_linux-zgemma = "4.4.35"

#Provider
PREFERRED_PROVIDER_virtual/kernel ?= "linux-zgemma"
PREFERRED_PROVIDER_virtual/egl ?= "zgemma-mali-3798mv200"
PREFERRED_PROVIDER_virtual/libgles1 ?= "zgemma-mali-3798mv200"
PREFERRED_PROVIDER_virtual/libgles2 ?= "zgemma-mali-3798mv200"
PREFERRED_PROVIDER_virtual/libgbm ?= "zgemma-mali-3798mv200"
PREFERRED_PROVIDER_virtual/libgl ?= "mesa"
PREFERRED_PROVIDER_virtual/mesa ?= "mesa"
PREFERRED_PROVIDER_virtual/blindscan-dvbc ?= "zgemma-dvb-modules-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbs ?= "zgemma-dvb-modules-${MACHINE}"
PREFERRED_PROVIDER_virtual/gstreamer1.0-dvbmediasink ?= "gstreamer1.0-plugin-dvbmediasink"
PREFERRED_PROVIDER_virtual/enigma2-mediaservice ?= "enigma2-plugin-systemplugins-servicehisilicon"
PREFERRED_PROVIDER_virtual/kodi ?= "stb-kodi-${MACHINE}"

# require conf/machine/include/arm/arch-armv8.inc
require conf/machine/include/arm/armv7a/tune-cortexa15.inc
require conf/machine/include/zgemma-essential.inc

# Gstreamer DVB mediasink
DVBMEDIASINK_CONFIG = "--with-h265 --with-vb8 --with-vb9 --with-wma --with-wmv --with-pcm --with-eac3 --with-amr"

# E2 OSD settings
EXTRA_OECONF:append:pn-enigma2 = " --with-alphablendingacceleration=always --with-blitaccelerationthreshold=250 --with-fillaccelerationthreshold=190000"

# Qt
PACKAGECONFIG_GL:pn-qtbase = " "
PACKAGECONFIG:append:pn-qtbase = " gles2 linuxfb"

MACHINE_EXTRA_RRECOMMENDS = " \
	ffmpeg \
	ntfs-3g \
	"

MACHINE_FEATURES += " alsa usbhost wlan 3dtv switchoff osdposition hdmicec qtevent1"

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
	cp ${IMGDEPLOYDIR}/${IMAGE_NAME}.ubi ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/rootfs.ubi; \
	cp ${DEPLOY_DIR_IMAGE}/uImage ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/uImage; \
	cp ${IMGDEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/bootoptions.yaffs ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/bootoptions.yaffs; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/fastboot.bin ${IMGDEPLOYDIR}/${IMAGEDIR}/fastboot.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/update_bootargs_${MACHINE}.bin ${IMGDEPLOYDIR}/${IMAGEDIR}/bootargs.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/fastboot.bin ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/fastboot.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/bootargs.bin ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/bootargs.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/pq_param.bin ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/pq_param.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/baseparam.img ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/baseparam.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/logo.img ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/logo.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/recovery.bin ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/recovery.bin; \
	echo "${IMAGEVERSION}" > ${IMGDEPLOYDIR}/${IMAGEDIR}/imageversion; \
	cd ${IMGDEPLOYDIR}/${IMAGEDIR}; \
	zip -r ../${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip *; \
	cd ..; \
	rm -Rf ${IMAGEDIR}; \
	"

IMAGE_CMD:zgemmafastboot8gb:append = " \
	mkdir -p ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}; \
	mkdir -p ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}; \
	bzip2 ${IMGDEPLOYDIR}/${IMAGE_NAME}.tar; \
	cp ${DEPLOY_DIR_IMAGE}/uImage ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/uImage; \
	cp ${IMGDEPLOYDIR}/${IMAGE_NAME}.tar.bz2 ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/rootfs.tar.bz2; \
	echo "${IMAGEVERSION}" > ${IMGDEPLOYDIR}/${IMAGEDIR}/${MACHINE}/imageversion; \
	echo "${IMAGEVERSION}_emmc.zip" > ${IMGDEPLOYDIR}/${IMAGEDIR}/unforce_${MACHINE}.txt; \
	echo "Rename unforce_${MACHINE}.txt to force_${MACHINE}.txt and move it to the root of your usb-stick" > ${IMGDEPLOYDIR}/${IMAGEDIR}/force_${MACHINE}_READ.ME; \
	echo "When enter the recovery menu you're forcing that the image ${IMAGEVERSION}_emmc.zip will been installed into linux-slot1" >> ${IMGDEPLOYDIR}/${IMAGEDIR}/force_${MACHINE}_READ.ME; \
	cp ${IMGDEPLOYDIR}/bootoptions.img ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/bootoptions.img; \
	cp ${IMGDEPLOYDIR}/misc-boot.img ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/misc-boot.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/fastboot.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/fastboot.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/bootargs.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/bootargs.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/fastboot.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/fastboot.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/bootargs.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/bootargs.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/pq_param.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/pqparam.bin; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/baseparam.img ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/baseparam.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/logo.img ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/logo.img; \
	cp ${DEPLOY_DIR_IMAGE}/zgemma-partitions-${MACHINE}/${MACHINE}/recovery.bin ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/recovery.bin; \
	echo "To access the recovery image press immediately by power-up the system a remote-key button untill the display says boot" >> ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/RECOVERY_${MACHINE}_READ.ME; \
	cd ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}; \
	zip -r ../${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_recovery_emmc.zip *; \
	cd ..; \
	cp ${IMGDEPLOYDIR}/${IMAGE_NAME}.userdata.ext4 ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/userdata.ext4; \
	cp ${DEPLOY_DIR_IMAGE}/uImage ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}/${MACHINE}/uImage; \
	cd ${IMGDEPLOYDIR}/${IMAGEDIRRECOVERY}; \
	zip -r ../${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_full_recovery_emmc.zip *; \
	cd ..; \
	rm -Rf ${IMAGEDIRRECOVERY}; \
	cd ${IMGDEPLOYDIR}/${IMAGEDIR}; \
	zip -r ../${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_emmc.zip *; \
	cd ..; \
	rm -Rf ${IMAGEDIR}; \
	"
