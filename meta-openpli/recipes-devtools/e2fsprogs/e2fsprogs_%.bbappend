FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SRC_URI:append:mipsel = " \
	file://Revert-mke2fs-enable-the-metadata_csum.patch \
	"
