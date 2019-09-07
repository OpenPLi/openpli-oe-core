FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-pp-Guard-fix-for-really-old-bug-in-glibc-libcrypt.patch \
"
