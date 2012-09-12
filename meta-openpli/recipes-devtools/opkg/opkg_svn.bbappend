PRINC = "2"

SRC_URI += "file://sanity-check-provides.patch \
	file://fix_uname_cache.patch \
	"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
