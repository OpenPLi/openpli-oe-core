DESCRIPTION = "RapidFuzz provides libraries for fuzzy string matching in various programming languages."
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://${S}/README.md;md5=cd30fe688968c142c6223b363c0bcd3b"

SRC_URI="git://github.com/rapidfuzz/rapidfuzz;branch=main;protocol=https"

S = "${WORKDIR}/git"

inherit pkgconfig setuptools3

PV = "3.9.7.+git${SRCPV}"
PKGV = "3.9.7+git${GITPKGV}"
