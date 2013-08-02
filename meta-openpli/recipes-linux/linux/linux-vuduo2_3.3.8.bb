require linux-vuplus-3.3.8.inc

MACHINE_KERNEL_PR_append = "${PR_INC}.6"

SRC_URI += " \
	file://remove_genet1.patch;patch=1;pnum=1 \
	file://nand_base.patch;patch=1;pnum=1 \
	file://brcm_s3_wol.patch;patch=1;pnum=1 \
"
