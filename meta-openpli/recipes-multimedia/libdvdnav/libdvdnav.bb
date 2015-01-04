SUMMARY = "DVD navigation multimeda library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libdvdread"
PV = "5.0.2"

inherit autotools pkgconfig git-project

SRCREV = "0a0b2ff7f1f71433c6dba5fd3d447d0a7a2c6885"
SRC_URI = "git://git.videolan.org/libdvdnav.git"
