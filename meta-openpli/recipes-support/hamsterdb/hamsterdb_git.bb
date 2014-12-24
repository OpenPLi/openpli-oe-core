DESCRIPTION = "HamsterDB"
LICENSE = "GPLv3"

LIC_FILES_CHKSUM = "file://COPYING.GPL3;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "boost"

SRCREV = "1447ba4eb217532e8fb49c4a84a0dc3b982a3ffe"

SRC_URI = "git://github.com/cruppstahl/hamsterdb.git;protocol=git"

S = "${WORKDIR}/git"

inherit gitpkgv autotools

PV = "2.1.9+git${SRCPV}"
PKGV = "2.1.9+git${GITPKGV}"
