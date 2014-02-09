DESCRIPTION="AiO screenshot grabber"
MAINTAINER = "PLi team"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "jpeg libpng zlib"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

SRC_URI = "git://git.code.sf.net/p/openpli/aio-grab;protocol=git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "ac_cv_prog_c_openmp=-fopenmp"
