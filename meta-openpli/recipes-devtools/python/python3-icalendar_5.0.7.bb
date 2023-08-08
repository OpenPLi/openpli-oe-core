SUMMARY = "The icalendar package is a parser/generator of iCalendar files for use with Python."
HOMEPAGE = "http://icalendar.readthedocs.org"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=1b2957cd26c589d0defcb357be630e80"

DEPENDS = "${PYTHON_PN}-pytz ${PYTHON_PN}-dateutil"
RDEPENDS:${PN} = "${PYTHON_PN}-pytz ${PYTHON_PN}-dateutil"

SRC_URI = "https://files.pythonhosted.org/packages/7b/cb/ab742b444f6a25a349f061f1d661060060191e065f0aa815ba1bf989bf5c/icalendar-${PV}.tar.gz"

SRC_URI[md5sum] = "338c8791e989554273705e3004843b0d"
SRC_URI[sha256sum] = "e306014a64dc4dcf638da0acb2487ee4ada57b871b03a62ed7b513dfc135655c"

S = "${WORKDIR}/icalendar-${PV}"

inherit setuptools3

include ${PYTHON_PN}-package-split.inc
