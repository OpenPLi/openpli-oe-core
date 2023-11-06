SUMMARY = "biTStream is a set of C headers"
DESCRIPTION = "biTStream is a set of C headers allowing a simpler access to binary \
	structures such as specified by MPEG, DVB, IETF, SMPTE, IEEE, SCTE, etc."
SECTION = "multimedia"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=7decd8ef15ab16ed5436851272b61cf7"
CLEANBROKEN = "1"

inherit gitpkgv gittag

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://github.com/videolan/bitstream.git;protocol=https;branch=master"
SRC_URI := "${SRC_ORIGIN} "
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

do_compile:prepend() {
	sed -i 's#/usr/local#/usr#' ${S}/Makefile
}

do_install:append() {
	install -d ${D}${includedir}
}

