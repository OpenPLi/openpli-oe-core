SUMMARY = "DVD access multimeda library"
SECTION = "libs/multimedia"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=64e753fa7d1ca31632bc383da3b57c27"

SRC_URI = "git://github.com/xbmc/libdvdread.git;protocol=https;branch=nexus"

SRCREV = "3e15b0f6d85e810779191961cc9372322600774e"
S = "${WORKDIR}/git"
PV = "6.1.3-Next-Nexus-Alpha2"

inherit autotools lib_package binconfig pkgconfig

CFLAGS:append = " -D_XBMC=1"

