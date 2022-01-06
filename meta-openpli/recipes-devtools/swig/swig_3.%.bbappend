FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://remove-duplicate-macros.patch;apply=no \
	"
