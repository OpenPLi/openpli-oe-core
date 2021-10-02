FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"

SRC_URI_append_mipsel = " \
	file://Revert-mke2fs-enable-the-metadata_csum.patch \
	"
