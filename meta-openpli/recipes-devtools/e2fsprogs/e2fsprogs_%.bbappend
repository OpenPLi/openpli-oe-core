PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"

SRC_URI_append_dm8000 = " \
	file://Revert-mke2fs-enable-the-metadata_csum.patch \
	"
