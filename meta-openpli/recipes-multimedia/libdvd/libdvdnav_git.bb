SUMMARY = "library for DVD navigation features"
SECTION = "libs/multimedia"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libdvdread"

SRC_URI = "git://github.com/xbmc/libdvdnav.git;protocol=https;branch=nexus"

SRCREV = "25229a54b2c4be9930d677f6abe3a0025514f3db"
S = "${WORKDIR}/git"
PV = "6.1.1-Next-Nexus-Alpha2-2"

inherit autotools lib_package binconfig pkgconfig

CONFIGUREOPTS:remove = "--disable-silent-rules"

