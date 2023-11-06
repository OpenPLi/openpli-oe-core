SUMMARY = "C++ parsing library for Service Information (SI) in DVB systems"
AUTHOR = "Andreas Oberritter"
SECTION = "libs/multimedia"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

PV = "0.3.9+git${SRCPV}"
PKGV = "0.3.9+git${GITPKGV}"

SRC_ORIGIN ?= "git://github.com/jack2015/libdvbsi.git;protocol=https;branch=master"
SRC_URI := "${SRC_ORIGIN} "

S = "${WORKDIR}/git"

inherit autotools pkgconfig gitpkgv
