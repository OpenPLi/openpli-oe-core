# dreambox.inc
# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"


EXTRA_IMAGEDEPENDS += "dreambox-secondstage-${MACHINE}"

GLIBC_EXTRA_OECONF = "--with-tls --with-__thread"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "dreambox-dvb-modules-${MACHINE}"

KERNEL_MODULE_AUTOLOAD += "xfs"

MACHINE_EXTRA_RRECOMMENDS = " \
	gst-plugin-dreambox-dvbmediasink \
	gst-plugin-dtsdownmix \
	ntfs-3g \
	kernel-module-cifs \
	kernel-module-hmac \
	kernel-module-md4 \
	kernel-module-ecb \
	dreambox-boot-progress \
	"

MACHINE_FEATURES += "alsa usbhost 3dtv switchoff tpm dreambox"
# Add "dreambox" override for recipes
MACHINEOVERRIDES .= ":dreambox"

PREFERRED_PROVIDER_virtual/bootloader = "dreambox-secondstage-${MACHINE}"
PREFERRED_PROVIDER_virtual/kernel = "linux-dreambox"

PREFERRED_PROVIDER_virtual/blindscan-dvbs = "dreambox-blindscan-utils"
PREFERRED_PROVIDER_virtual/blindscan-dvbc = "dreambox-blindscan-utils"

# Dreamboxes need old kernel headers to work
LINUX_LIBC_HEADERS_VERSION = "3.1"
PREFERRED_VERSION_linux-libc-headers = "${LINUX_LIBC_HEADERS_VERSION}"
PREFERRED_VERSION_linux-libc-headers-native = "${LINUX_LIBC_HEADERS_VERSION}"
PREFERRED_VERSION_nativesdk-linux-libc-headers = "${LINUX_LIBC_HEADERS_VERSION}"

PREFERRED_VERSION_linux-dreambox = "3.2"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_CONSOLE = "${@base_contains('OPENPLI_FEATURES', 'usbconsole', 'ttyS0,115200', 'null', d)}"

DREAMBOX_BUILDIMAGE = "buildimage --arch ${MACHINE} ${EXTRA_BUILDCMD} \
	--erase-block-size ${DREAMBOX_ERASE_BLOCK_SIZE} \
	--flash-size ${DREAMBOX_FLASH_SIZE} \
	--sector-size ${DREAMBOX_SECTOR_SIZE}"
