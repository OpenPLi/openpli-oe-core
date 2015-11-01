DESCRIPTION = "SAT>IP client"
MAINTAINER = "PLi team"
require conf/license/openpli-gplv2.inc

inherit gitpkgv

SRCREV = "3db78c34e54e059dee33feb5f7208b7fb9ac1262"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

SRC_URI = "git://github.com/eriksl/satip-client.git;protocol=git"
FILES_${PN} = "/usr/sbin/satip-client"
S = "${WORKDIR}/git"
BUILD = "${WORKDIR}/build"

inherit autotools

do_install() {
	install -m 755 -d ${D}/usr/sbin
	install -m 755 ${BUILD}/satip_client ${D}/usr/sbin/satip-client
}
