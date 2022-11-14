SUMMARY = "treq: High-level Twisted HTTP Client API"
DESCRIPTION = "treq is an HTTP library inspired by requests but written \
on top of Twisted's Agents. It provides a simple, higher level API \
for making HTTP requests when using Twisted."
SECTION = "devel/python"

MAINTAINER = "Tom Most <twm@freecog.net>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5dd67fd17479587f7093769d95ef186"

SRC_URI[sha256sum] = "02ea86383fc4b57539c45a965eaa1e9fd28302cdf382d21da8430050c97be9b8"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-twisted-web \
"

# setup.py of treq needs this.
DEPENDS = "\
    ${PYTHON_PN}-pip-native \
    ${PYTHON_PN}-wheel-native \
"

RDEPENDS_${PN}-dbg = "${PN}"
FILES_${PN}-dbg = " \
    ${libdir}/${PYTHON_DIR}/site-packages/${PN}-*.egg-info \
    ${libdir}/${PYTHON_DIR}/site-packages/${PN}/test \
"

S = "${WORKDIR}/${PN}-${PV}"

inherit pypi setuptools3

include ${PYTHON_PN}-package-split.inc
