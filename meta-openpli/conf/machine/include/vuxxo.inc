MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	vuplus-dvb-modules-${MACHINE} \
	"

include conf/machine/include/autoload-filesystems.inc

MACHINE_EXTRA_RRECOMMENDS = " \
	vuplus-shutdown \
	gst-plugin-dvbmediasink \
	ntfs-3g \
	"

EXTRA_IMAGEDEPENDS += "\
	vuplus-opera-browser-plugin \
	"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

GLIBC_EXTRA_OECONF = "--with-tls"

PREFERRED_VERSION_linux-${MACHINE} = "3.1.1"
PREFERRED_VERSION_linux-libc-headers = "3.1"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"

PREFERRED_PROVIDER_virtual/blindscan-dvbc = "vuplus-blindscan-utils-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "vuplus-blindscan-utils-${MACHINE}"

IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096 -F"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

IMAGEDIR ?= "${MACHINE}"
EXTRA_IMAGECMD_COMPAT = " --eraseblock=0x20000 -n -l "

IMAGE_CMD_ubi_prepend = " \
        mkfs.jffs2 --root=${IMAGE_ROOTFS}/boot --faketime \
        --disable-compressor=lzo --compression-mode=size \
        --output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 \
        ${EXTRA_IMAGECMD_COMPAT}; rm -rf ${IMAGE_ROOTFS}/boot/*; \
        "

IMAGE_CMD_ubi_append = " \
        mkdir -p ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}; \
        cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/root_cfe_auto.jffs2; \
        cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/boot_cfe_auto.jffs2; \
        gzip -f -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/kernel_cfe_auto.bin; \
        cd ${DEPLOY_DIR_IMAGE}; \
        zip ${IMAGE_NAME}_usb.zip vuplus/${UPDATE_DIR}/*; \
        rm -rf vuplus; \
        "

TARGET_FPU = "hard"

MACHINE_FEATURES += "alsa usbhost wifi 3dtv switchoff osdposition hdmicec"

require conf/machine/include/tune-mips32.inc
