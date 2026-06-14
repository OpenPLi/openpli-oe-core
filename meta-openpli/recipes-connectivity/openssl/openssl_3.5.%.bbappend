PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://openssl-cnf.patch"
