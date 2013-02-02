require linux-vuplus-3.3.6.inc

MACHINE_KERNEL_PR_append = "${PR_INC}.2"

SRC_URI += " \
	file://bmips-no-array-bounds.patch;striplevel=1 \
	file://nfs-max-rwsize-8k.patch \
	"
