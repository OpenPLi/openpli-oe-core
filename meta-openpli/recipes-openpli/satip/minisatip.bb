DESCRIPTION = "SAT>IP server"
MAINTAINER = "PLi team"
require conf/license/openpli-gplv2.inc

inherit gitpkgv

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

SRC_URI = "https://github.com/eriksl/minisatip"
FILES_${PN} = "${sbindir}/minisatip"
S = "${WORKDIR}/git"
BUILD = "${WORKDIR}/build"

inherit autotools

do_install() {
	install -m 755 -d ${D}${sbindir}
	install -m 755 ${BUILD}/minisatip ${D}${sbindir}
}
