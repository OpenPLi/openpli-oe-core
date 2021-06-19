SUMMARY = "tuxbox libtuxtxt"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "libpng freetype zlib"

inherit  gitpkgv

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/tuxtxt.git \
	file://0001-libtuxtxt-add-function-prototype-for-writeproc.patch \
	file://0002-libtuxtxt-fix-build-warnings.patch \
	file://0003-libtuxtxt-remove-unnecessary-unset-DEBUG-workaround.patch \
	file://0004-libtuxtxt-fix-build-with-musl.patch \
	"

S = "${WORKDIR}/git/libtuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-boxtype=generic DVB_API_VERSION=5 --without-debug"
