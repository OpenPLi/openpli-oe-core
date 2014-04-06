include conf/machine/include/openpli.inc

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	vuplus-dvb-modules-${MACHINE} \
	"

include conf/machine/include/autoload-filesystems.inc

MACHINE_EXTRA_RRECOMMENDS = " \
	vuplus-shutdown \
	gst-plugin-vuplus-dvbmediasink \
	ntfs-3g \
	"

EXTRA_IMAGEDEPENDS += "\
	enigma2-plugin-systemplugins-firmwareupgrade \
	enigma2-plugin-systemplugins-remotecontrolcode \
	enigma2-plugin-systemplugins-manualfancontrol \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-streamtv \
	enigma2-plugin-extensions-addstreamurl \
	enigma2-plugin-systemplugins-zappingmodeselection \
	streamproxy \
"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

DVBMEDIASINK_CONFIG = "--with-pcm"

PREFERRED_VERSION_linux-${MACHINE} = "3.3.8"
PREFERRED_VERSION_linux-libc-headers = "3.3"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"

PREFERRED_PROVIDER_virtual/blindscan-dvbc = "vuplus-blindscan-utils-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "vuplus-blindscan-utils-${MACHINE}"

IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

IMAGEDIR ?= "${MACHINE}"
EXTRA_IMAGECMD_COMPAT = " --eraseblock=0x20000 -n -l "

IMAGE_CMD_ubi_prepend = " \
	cp ${IMAGE_ROOTFS}/tmp/vmlinux.gz ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.vmlinux.gz; \
	cp ${IMAGE_ROOTFS}/boot/initrd_cfe_auto.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.initrd_cfe_auto.bin; \
	rm -f ${IMAGE_ROOTFS}/tmp/vmlinux.gz; \
	rm -f ${IMAGE_ROOTFS}/boot/initrd_cfe_auto.bin; \
"

IMAGE_CMD_ubi_append = " \
	mkdir -p ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}; \
	cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/root_cfe_auto.bin; \
	cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.vmlinux.gz ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/kernel_cfe_auto.bin; \
	cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.initrd_cfe_auto.bin ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/initrd_cfe_auto.bin; \
	touch ${DEPLOY_DIR_IMAGE}/vuplus/${UPDATE_DIR}/reboot.update; \
	cd ${DEPLOY_DIR_IMAGE}; \
	zip ${IMAGE_NAME}_usb.zip vuplus/${UPDATE_DIR}/*; \
	rm -rf vuplus; \
"

TARGET_FPU = "hard"

MACHINE_FEATURES += "alsa usbhost wlan kernelwifi externalwifi 3dtv switchoff osdposition hdmicec fan"

require conf/machine/include/tune-mips32.inc
