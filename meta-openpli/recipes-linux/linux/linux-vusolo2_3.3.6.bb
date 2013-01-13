require linux-vuplus-3.3.6.inc

MACHINE_KERNEL_PR_append = "${PR_INC}.0"

SRC_URI += " \
	file://bmips-no-array-bounds.patch;striplevel=1 \
	"
