SUMMARY = "tuxbox libtuxtxt"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "libpng freetype zlib"

inherit gitpkgv

SRCREV = "a1b42559e865bc9e58a35a4c3479f32c729b7f51"
GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/tuxtxt.git"

S = "${WORKDIR}/git/libtuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

EXTRA_OECONF = "--with-boxtype=generic DVB_API_VERSION=5"

inherit autotools pkgconfig
