FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "\
	file://0027-workaround-build-with-glibc228.patch \
	"
