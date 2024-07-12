SUMMARY = "The icalendar package is a parser/generator of iCalendar files for use with Python."
HOMEPAGE = "http://icalendar.readthedocs.org"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=1b2957cd26c589d0defcb357be630e80"

DEPENDS = "python3-pytz python3-dateutil"
RDEPENDS:${PN} = "python3-pytz python3-dateutil"

SRC_URI[md5sum] = "2c1c9d6a3b2f4d88297854122e513e21"
SRC_URI[sha256sum] = "92799fde8cce0b61daa8383593836d1e19136e504fa1671f471f98be9b029706"

inherit pypi setuptools3

include python3-package-split.inc
