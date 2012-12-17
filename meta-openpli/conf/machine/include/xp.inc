MACHINE_ESSENTIAL_EXTRA_RDEPENDS = "\
	xp-dvb-modules-${MACHINE} \
	"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
	"

include conf/machine/include/autoload-filesystems.inc

MACHINE_EXTRA_RRECOMMENDS = " \
	gst-plugin-dvbmediasink \
	ntfs-3g \
	"

CHIPSET = "bcm7358"

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dtsdownmix"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

GLIBC_EXTRA_OECONF = "--with-tls --with-__thread"

PREFERRED_PROVIDER_virtual/kernel = "linux-xp"

PREFERRED_VERSION_linux-xp = "3.5.1"
PREFERRED_VERSION_linux-libc-headers = "3.3"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_CONSOLE = "null"

IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

IMAGEDIR ?= "${MACHINE}"

IMAGE_CMD_ubi_append = " \
	mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
	cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.bin; \
	gzip -c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/kernel.bin; \
	echo ${DISTRO_NAME}-${DISTRO_VERSION}-${DATE} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
	echo "rename this file to 'force' to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
	cd ${DEPLOY_DIR_IMAGE}; \
	zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}-${DATETIME}_usb.zip ${IMAGEDIR}/*; \
	rm -Rf ${IMAGEDIR}; \
	"

TARGET_FPU = "soft"

MACHINE_FEATURES += "alsa usbhost wifi 3dtv switchoff textlcd osdposition hdmicec"

require conf/machine/include/tune-mips32.inc

MACHINE_KERNEL_PR = "r0"
