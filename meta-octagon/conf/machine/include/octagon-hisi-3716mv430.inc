MACHINE_KERNEL_PR = "r0"
KERNEL_MODULE_AUTOLOAD += "xfs"

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = "\
    octagon-dvb-modules-${MACHINE} \
    octagon-loadmodules-${SOC_FAMILY} \ 
    octagon-libs-${MACHINE} \
    octagon-hihalt-${MACHINE} \
    octagon-reader-${MACHINE} \
    octagon-libreader-${SOC_FAMILY} \
    ${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbs", "octagon-blindscan-${SOC_FAMILY}" , "", d)} \
    octagon-partitions-${MACHINE} \
    octagon-buildimage \
    gptfdisk \
"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
	${@bb.utils.contains('MACHINE_FEATURES', 'wol', 'octagon-ethwol' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'emmc', 'partitions-by-name' , '', d)} \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-mt7601u \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-rtl8188eu \
	firmware-rtl8192eu \
	firmware-rtl8192cu \
	firmware-rtl8xxxu \
	firmware-zd1211 \
	\
	rtl8192eu \
	\
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-mt7601u \
	kernel-module-r8712u \
	kernel-module-r8723au \
	kernel-module-r8188eu \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-rtl8192cu \
	kernel-module-rtl8xxxu \
	kernel-module-zd1211rw \
	"
	
MACHINE_FEATURES += " alsa usbhost wlan switchoff osdposition hdmicec"

IMAGE_CLASSES  += "octagon-ubi_image"
IMAGE_FSTYPES += "octagonubi"

MKUBIFS_ARGS = "-m 2048 -e 126976 -c 3800"
UBINIZE_ARGS = "-m 2048 -p 128KiB -s 2048"
UBI_VOLNAME = "rootfs"

TARGET_ARCH = "arm"
DEFAULTTUNE = "cortexa7hf"

PREFERRED_VERSION_linux-octagon = "4.4.176"

PREFERRED_PROVIDER_virtual/kernel = "linux-octagon"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "octagon-blindscan-${SOC_FAMILY}"

PREFERRED_RPROVIDER_showiframe = "octagon-showiframe-${SOC_FAMILY}"
PREFERRED_RPROVIDER_aio-grab = "octagon-grab-${SOC_FAMILY}"
PREFERRED_PROVIDER_virtual/gstreamer1.0-dvbmediasink ?= "gstreamer1.0-plugin-dvbmediasink"

require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# Qt
PACKAGECONFIG_GL:pn-qtbase = " "
PACKAGECONFIG:append:pn-qtbase = " linuxfb no-opengl"


MACHINE_EXTRA_RRECOMMENDS = " \
	ffmpeg \
	ntfs-3g \
	"


# Gstreamer dvbmediasink
DVBMEDIASINK_CONFIG = "--with-h265 --with-vb8 --with-wma --with-wmv --with-pcm  --with-eac3 --with-amr --with-spark"

IMAGEDIR ?= "octagon/${MACHINE}"
IMGDEPLOYDIR ?= "${DEPLOY_DIR_IMAGE}"
IMAGEVERSION := "${DISTRO_NAME}-${DISTRO_VERSION}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"
IMAGE_CMD:octagonubi:append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs1; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs2; \
    cp -Rf ${IMAGE_ROOTFS}/* ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs1/; \
    mkfs.ubifs -F -d ${DEPLOY_DIR_IMAGE}/userdata -m 4096 -o ${DEPLOY_DIR_IMAGE}/rootfs.ubifs -e 253952 -c 1900; \
    echo \[ubifs\] > ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    echo mode=ubi >> ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    echo image=${DEPLOY_DIR_IMAGE}/rootfs.ubifs >> ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    echo vol_id=0 >> ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    echo vol_type=dynamic >> ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    echo vol_name=ubifs >> ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    echo vol_flags=autoresize >> ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
    ubinize -o ${DEPLOY_DIR_IMAGE}/${ROOTFS_FILE} -m 4096 -p 0x40000 ${DEPLOY_DIR_IMAGE}/rootfs.ubicfg; \
	mv ${DEPLOY_DIR_IMAGE}/${ROOTFS_FILE} ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/${ROOTFS_FILE}; \
    cd ${IMAGE_ROOTFS}; \
    tar -cvf ${DEPLOY_DIR_IMAGE}/rootfs.tar -C ${IMAGE_ROOTFS} .; \
    mv ${DEPLOY_DIR_IMAGE}/rootfs.tar ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.tar; \
    bzip2 ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.tar; \
    cp ${DEPLOY_DIR_IMAGE}/uImage ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
    cp ${DEPLOY_DIR_IMAGE}/uImage ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/${KERNEL_FILE}; \
    if [ -e ${DEPLOY_DIR_IMAGE}/logo-${DISTRO_NAME}.img ]; then \
    cp -f ${DEPLOY_DIR_IMAGE}/logo-${DISTRO_NAME}.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/logo.img; \
    fi; \
    echo ${IMAGEVERSION} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    cd ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions; \
    mkupdate -s 00000003-00000001-01010101 -f ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/nand_partitions.xml -d ${DEPLOY_DIR_IMAGE}/usb_update.bin; \ 
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_multiboot_ofgwrite.zip ${IMAGEDIR}/*; \
    zip -j ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip ${DEPLOY_DIR_IMAGE}/usb_update.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/bootargs.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/apploader.bin; \
	rm -f ${DEPLOY_DIR_IMAGE}/*.ubi; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ubifs; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.ubifs; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -Rf ${DEPLOY_DIR_IMAGE}/usb_update.bin; \
    rm -Rf ${IMAGEDIR}; \
    rm -Rf ${DEPLOY_DIR_IMAGE}/userdata; \
    "

IMAGE_CMD:prepend = " \
    rm -Rf ${IMAGE_ROOTFS}/tmp/*; \
    "
