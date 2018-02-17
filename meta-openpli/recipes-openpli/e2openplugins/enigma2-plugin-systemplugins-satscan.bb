MODULE = "Satscan"
DESCRIPTION = "Blind scan on DVB-S"
RDEPENDS_${PN} = "virtual/blindscan-dvbs"
RDEPENDS_${PN} += "python-subprocess"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit gitpkgv

PV = "1.3+git${SRCPV}"
PKGV = "${PV}"
PR = "r0"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc
