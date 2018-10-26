inherit pypi setuptools
require python-twisted.inc

SRC_URI += " \
           file://fix-writing-after-channel-is-closed.patch \
           "

RDEPENDS_${PN}-core += "${PYTHON_PN}-contextlib"

