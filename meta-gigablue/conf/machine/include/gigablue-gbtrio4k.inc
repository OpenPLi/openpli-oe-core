KERNEL_MODULE_AUTOLOAD += "xfs"

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"


MTD_KERNEL = "mmcblk0p12"
MTD_ROOTFS = "mmcblk0p16"
KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.tar.bz2"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    gigablue-dvb-modules-${MACHINE} \
    gigablue-loadmodules-${MACHINE} \ 
    gigablue-opengl-${MACHINE} \
    gigablue-libs-${MACHINE} \
    gigablue-hihalt-${MACHINE} \
    gigablue-reader-${MACHINE} \
    gigablue-libreader-${MACHINE} \
    ${@bb.utils.contains("MACHINE_FEATURES", "blindscan", "gigablue-blindscan-${MACHINE}" , "", d)} \    
    gigablue-partitions-${MACHINE} \
    gigablue-buildimage \
    e2fsprogs-resize2fs \
    gptfdisk \
"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
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
	kernel-module-88x2bu \
	gigablue-uwe5621-firmware \
	gigablue-hciattach-gbtrio4kpro \
	gigablue-bluetooth-keymaps \
	"

MACHINE_FEATURES += " alsa usbhost wlan switchoff osdposition hdmicec"

IMAGE_CLASSES  += "gigablue-emmc_image"
IMAGE_FSTYPES += "gigablueemmc"
IMAGE_ROOTFS_SIZE ?= "524288"
#CMDLINE ?= "console=ttyAMA0,115200n8"

TARGET_ARCH = "arm"
DEFAULTTUNE = "cortexa15hf-neon-vfpv4"

PREFERRED_VERSION_linux-gigablue = "4.4.35"

PREFERRED_PROVIDER_virtual/kernel = "linux-gigablue"
PREFERRED_PROVIDER_virtual/egl = "gigablue-opengl-${MACHINE}" 
PREFERRED_PROVIDER_virtual/libgles1 = "gigablue-opengl-${MACHINE}"
PREFERRED_PROVIDER_virtual/libgles2 = "gigablue-opengl-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "gigablue-blindscan-${MACHINE}"
PREFERRED_PROVIDER_virtual/kodi = "stb-kodi-gbmv200"

PREFERRED_RPROVIDER_showiframe = "gigablue-showiframe-${MACHINE}"
PREFERRED_RPROVIDER_aio-grab = "gigablue-grab-${MACHINE}"
PREFERRED_PROVIDER_virtual/gstreamer1.0-dvbmediasink ?= "gstreamer1.0-plugin-dvbmediasink"

require conf/machine/include/arm/armv7a/tune-cortexa15.inc

# Qt
PACKAGECONFIG_GL:pn-qtbase = "gles2 eglfs linuxfb"
PACKAGECONFIG_FB:pn-qtbase = " "

MACHINE_EXTRA_RRECOMMENDS = " \
	ffmpeg \
	ntfs-3g \
	"

DVBMEDIASINK_CONFIG = "--with-h265 --with-vb8 --with-vb9 --with-spark --with-wma --with-wmv --with-pcm --with-dts --with-eac3"

IMAGEDIR ?= "gigablue/${MACHINE}"
IMGDEPLOYDIR ?= "${DEPLOY_DIR_IMAGE}"
IMAGEVERSION := "${DISTRO_NAME}-${DISTRO_VERSION}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"

IMAGE_CMD:gigablueemmc:append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs1; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs2; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs3; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs4; \
    cp -fR ${IMAGE_ROOTFS}/* ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs1/; \
    cp ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/platforms/gbtrio4k_fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/fastboot.bin; \
    dd if=/dev/zero of=${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/rootfs.ext4 seek=${IMAGE_ROOTFS_SIZE} count=60 bs=1024; \
    mkfs.ext4 -F -i 4096 ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/rootfs.ext4 -d ${DEPLOY_DIR_IMAGE}/userdata; \
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
    mkupdate -s 00000003-00000001-01010101 -f ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/emmc_partitions.xml -d ${DEPLOY_DIR_IMAGE}/usb_update.bin; \ 
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_multiboot_ofgwrite.zip ${IMAGEDIR}/*; \
    zip -j ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip ${DEPLOY_DIR_IMAGE}/usb_update.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/bootargs.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/apploader.bin; \
    rm -Rf ${IMAGEDIR}; \
    "

IMAGE_CMD:gigablueemmc:append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}pro; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs1; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs2; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs3; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs4; \
    cp -fR ${IMAGE_ROOTFS}/* ${DEPLOY_DIR_IMAGE}/userdata/linuxrootfs1/; \
    cp ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/platforms/gbtrio4kpro_fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/fastboot.bin; \
    dd if=/dev/zero of=${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/rootfs.ext4 seek=${IMAGE_ROOTFS_SIZE} count=60 bs=1024; \
    mkfs.ext4 -F -i 4096 ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/rootfs.ext4 -d ${DEPLOY_DIR_IMAGE}/userdata; \
    cd ${IMAGE_ROOTFS}; \
    tar -cvf ${DEPLOY_DIR_IMAGE}/rootfs.tar -C ${IMAGE_ROOTFS} .; \
    mv ${DEPLOY_DIR_IMAGE}/rootfs.tar ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}pro/rootfs.tar; \
    bzip2 ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}pro/rootfs.tar; \
    cp ${DEPLOY_DIR_IMAGE}/uImage ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}pro/${KERNEL_FILE}; \
    cp ${DEPLOY_DIR_IMAGE}/uImage ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/${KERNEL_FILE}; \
    if [ -e ${DEPLOY_DIR_IMAGE}/logo-${DISTRO_NAME}.img ]; then \
        cp -f ${DEPLOY_DIR_IMAGE}/logo-${DISTRO_NAME}.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/logo.img; \
    fi; \
    echo ${IMAGEVERSION} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}pro/imageversion; \
    cd ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions; \
    mkupdate -s 00000003-00000001-01010101 -f ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/emmc_partitions.xml -d ${DEPLOY_DIR_IMAGE}/usb_update.bin; \ 
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}pro_multiboot_ofgwrite.zip ${IMAGEDIR}pro/*; \
    zip -j ${DISTRO_NAME}-${DISTRO_VERSION}-gbtrio4kpro_usb.zip ${DEPLOY_DIR_IMAGE}/usb_update.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/bootargs.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/apploader.bin; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -f -f ${DEPLOY_DIR_IMAGE}/*.json; \
    rm -Rf ${DEPLOY_DIR_IMAGE}/usb_update.bin; \
    rm -Rf ${IMAGEDIR}pro; \
    rm -Rf ${DEPLOY_DIR_IMAGE}/userdata; \
    "

IMAGE_CMD:prepend = " \
    rm -Rf ${IMAGE_ROOTFS}/tmp/*; \
    "
