SUMMARY = "DVBlast is a simple and powerful MPEG-2/TS demux and streaming application"
DESCRIPTION = "DVBlast is written to be the core of a custom IRD, CID, or ASI gateway, \
	based on a PC with a Linux-supported card. It is very lightweight and stable, designed for 24/7 operation."
SECTION = "multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ed7e492ee44e70125a5d42e118354a13"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

DEPENDS = "bitstream libev"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://code.videolan.org/videolan/dvblast.git;protocol=https"
SRC_URI := "${SRC_ORIGIN} "

S = "${WORKDIR}/git"

inherit autotools-brokensep

do_compile_prepend() {
        sed -i 's#/usr/local#/usr#' ${S}/Makefile
}
