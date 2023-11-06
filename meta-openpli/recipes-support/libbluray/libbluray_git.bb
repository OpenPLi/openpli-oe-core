SUMMARY  = "Library to access Blu-Ray disk"
SECTION = "misc"
HOMEPAGE = "http://videolan.org"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM="file://COPYING;md5=435ed639f84d4585d93824e7da3d85da"

DEPENDS = "freetype fontconfig libxml2 libudfread"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://github.com/ShiftMediaProject/libbluray.git;branch=master;protocol=https"
SRC_URI := "${SRC_ORIGIN} "

inherit gittag autotools pkgconfig

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
