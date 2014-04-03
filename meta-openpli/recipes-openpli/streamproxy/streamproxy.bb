DESCRIPTION = "Streamproxy for live and file streaming and transcoding"
MAINTAINER = "PLi team"
DEPENDS = "boost"
PACKAGE_ARCH = "${MACHINE_ARCH}"
require conf/license/openpli-gplv2.inc

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

SRC_URI = "git://github.com/eriksl/streamproxy.git;protocol=git"

S = "${WORKDIR}/git"

inherit autotools

FILES_${PN} = "/usr/bin/streamproxy"
