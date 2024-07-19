ESCRIPTION = "RapidFuzz provides libraries for fuzzy string matching in various programming languages."
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://${S}/README.md;md5=d04c97bc3eaa7ca22c05ce1316aadc6a"

SRC_URI="git://github.com/rapidfuzz/rapidfuzz;branch=main;protocol=https"

S = "${WORKDIR}/git"

inherit pkgconfig setuptools3

SRCREV = "${AUTOREV}"
PV = "3.9.4.+git${SRCPV}"
PKGV = "3.9.4+git${GITPKGV}"
