MODULE = "Foreca"
DESCRIPTION = "Weather forecast for the upcoming 10 days"
RDEPENDS_${PN} = "${PYTHON_PN}-html ${PYTHON_PN}-requests"

inherit gitpkgv
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r1"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc

FILES:${PN} += "${sysconfdir}/enigma2/Foreca"
CONFFILES:${PN} = "${sysconfdir}/enigma2/Foreca/City.cfg ${sysconfdir}/enigma2/Foreca/Filter.cfg ${sysconfdir}/enigma2/Foreca/fav1.cfg ${sysconfdir}/enigma2/Foreca/fav2.cfg ${sysconfdir}/enigma2/Foreca/startservice.cfg"
