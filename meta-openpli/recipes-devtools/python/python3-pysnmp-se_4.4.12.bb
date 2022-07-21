DESCRIPTION = "A Pure Python SNMP Package"
SECTION = "devel/python"
PRIORITY = "optional"
RDEPENDS:${PN} = "python3-core"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=b15d29f500f748d1c2a15709769090a8"

SRCNAME = "pysnmp"
SRC_URI = "https://files.pythonhosted.org/packages/4e/75/72f64c451bf5884715f84f8217b69b4025da0b67628d611cd14a5b7db217/${SRCNAME}-${PV}.tar.gz"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

SRC_URI[md5sum] = "2222880259daf6e2cb322e938c818276"
SRC_URI[sha256sum] = "0c3dbef2f958caca96071fe5c19de43e9c1b0484ab02a0cf08b190bcee768ba9"

include python-package-split.inc
