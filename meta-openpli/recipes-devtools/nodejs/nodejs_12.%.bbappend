FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://fix-mips-build.patch"
SRC_URI += "file://0001-add-include-cstdint.patch"
