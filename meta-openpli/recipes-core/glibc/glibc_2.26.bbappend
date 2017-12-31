FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-malloc-Fix-tcache-leak-after-thread-destruction-BZ-2.patch"
