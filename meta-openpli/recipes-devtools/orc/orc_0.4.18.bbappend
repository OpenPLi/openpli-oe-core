FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC = "1"

SRC_URI += "file://disable-mips.patch"
