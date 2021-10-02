FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_mipsel_append = " \
	file://005-misc-rename-copy_file_range-to-copy_file_chunk.patch \
"
