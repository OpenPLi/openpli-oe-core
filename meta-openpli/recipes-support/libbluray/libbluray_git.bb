SUMMARY  = "Library to access Blu-Ray disk"
SECTION = "misc"
HOMEPAGE = "http://videolan.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=435ed639f84d4585d93824e7da3d85da"

DEPENDS = "libxml2"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "gitsm://code.videolan.org/videolan/libbluray.git;protocol=https"
SRC_URI := "${SRC_ORIGIN} "

inherit gittag setuptools3 autotools-brokensep pkgconfig

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

S="${WORKDIR}/git"

EXTRA_OECONF = " \
    --disable-bdjava-jar \
    --disable-doxygen-doc \
    --disable-doxygen-dot \
    --without-freetype \
    --without-fontconfig \
"
