MACHINEOVERRIDES =. "kernel-4.10:"
MACHINEOVERRIDES =. "bcm-v3d:"

PREFERRED_VERSION_linux-hd = "4.10.12"

IMAGE_CLASSES += " hd-emmc_image_multiboot"
IMAGE_FSTYPES += " hdemmc tar"
IMAGE_ROOTFS_SIZE ?= "294912"

PREFERRED_PROVIDER_virtual/blindscan-dvbs ?= "hd-dvb-modules-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbc ?= "hd-dvb-modules-${MACHINE}"

TARGET_ARCH = "arm"
DEFAULTTUNE = "cortexa15hf-neon-vfpv4"

require conf/machine/include/arm/armv7a/tune-cortexa15.inc
require conf/machine/include/hd.inc
