require conf/machine/include/et.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-etxx00-2g"

MKUBIFS_ARGS = "-m 2048 -e 126976 -c 8192"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

IMAGE_CMD_ubi_prepend = " \
	rm -Rf ${IMAGE_ROOTFS}/tmp/*; \
	"

MACHINE_FEATURES += "extrakernelwifi"

MACHINE_KERNEL_PR = "r0"
