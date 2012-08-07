require linux-vuplus-3.1.1.inc

MACHINE_KERNEL_PR_append += ".10"

SRC_URI += "\
	file://linux-sata_brcm.patch;striplevel=1 \
	"

