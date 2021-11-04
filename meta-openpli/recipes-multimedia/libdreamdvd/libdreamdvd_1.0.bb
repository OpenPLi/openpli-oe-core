SUMMARY = "libdvdnav wrapper for enigma2 based stbs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libdvdnav"

inherit autotools pkgconfig git-project

SRC_URI = "git://github.com/mirakels/libdreamdvd.git;protocol=https \
	file://move-function-pointer-away-from-header.patch \
"
