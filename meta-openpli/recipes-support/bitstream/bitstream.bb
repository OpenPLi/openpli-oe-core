SUMMARY = "biTStream is a set of C headers"
DESCRIPTION = "biTStream is a set of C headers allowing a simpler access to binary \
	structures such as specified by MPEG, DVB, IETF, SMPTE, IEEE, SCTE, etc."
SECTION = "multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7decd8ef15ab16ed5436851272b61cf7"
CLEANBROKEN = "1"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://code.videolan.org/videolan/bitstream.git;protocol=https"
SRC_URI := "${SRC_ORIGIN} "
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

do_compile_prepend() {
	sed -i 's#/usr/local#/usr#' ${S}/Makefile
}

do_install_append() {
	install -d ${D}${includedir}
}

