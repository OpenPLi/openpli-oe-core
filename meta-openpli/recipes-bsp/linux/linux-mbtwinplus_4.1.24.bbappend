FILESEXTRAPATHS_prepend := "${THISDIR}/gcc:"
SRC_URI += " \
	file://kernel-add-support-for-gcc10.patch \
	file://kernel-add-support-for-gcc11.patch \
	"
