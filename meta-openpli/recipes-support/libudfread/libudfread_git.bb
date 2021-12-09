SUMMARY  = "UDF reader"
SECTION = "misc"
HOMEPAGE = "http://videolan.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://code.videolan.org/videolan/libudfread.git;branch=master;protocol=https"
SRC_URI := "${SRC_ORIGIN} "

inherit gittag autotools-brokensep pkgconfig

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

S="${WORKDIR}/git"
