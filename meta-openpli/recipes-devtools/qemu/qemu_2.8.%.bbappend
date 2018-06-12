FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://memfd.patch \
"
