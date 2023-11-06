SUMMARY  = "UDF reader"
SECTION = "misc"
HOMEPAGE = "http://videolan.org"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://github.com/disc-kuraudo//libudfread.git;branch=master;protocol=https"
SRC_URI := "${SRC_ORIGIN} "

inherit gittag autotools pkgconfig

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

S="${WORKDIR}/git"
