DESCRIPTION = "SAT>IP client"
MAINTAINER = "PLi team"
require conf/license/openpli-gplv2.inc

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

SRC_URI = " \
	git://github.com/eriksl/satip-client.git;protocol=git \
	file://satipclient.sh \
"
FILES_${PN} = "/usr/sbin/satip-client"
S = "${WORKDIR}/git"
BUILD = "${WORKDIR}/build"

inherit gitpkgv autotools update-rc.d

INITSCRIPT_NAME = "satipclient"
INITSCRIPT_PARAMS = "defaults 60 "

do_install_append() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/satipclient.sh ${D}/etc/init.d/satipclient
	install -m 755 -d ${D}/usr/sbin
	install -m 755 ${BUILD}/satip_client ${D}/usr/sbin/satip-client
}
