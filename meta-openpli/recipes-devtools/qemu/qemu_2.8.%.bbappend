FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://memfd.patch \
	file://0001-linux-user-rename-gettid-to-sys_gettid-to-avoid-clas.patch \
	file://0002-linux-user-assume-__NR_gettid-always-exists.patch \
"
