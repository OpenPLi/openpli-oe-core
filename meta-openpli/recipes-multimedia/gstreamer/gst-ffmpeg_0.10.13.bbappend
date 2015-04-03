PR = "r9"

SRC_URI += "file://fix_configure.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG = ""
