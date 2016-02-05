DESCRIPTION="AiO screenshot grabber"
MAINTAINER = "PLi team"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "jpeg libpng zlib"

SRCREV = "009fd8b71d3fd974df4048cc4de038dcb94a778f"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/${BPN}.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "ac_cv_prog_c_openmp=-fopenmp"
