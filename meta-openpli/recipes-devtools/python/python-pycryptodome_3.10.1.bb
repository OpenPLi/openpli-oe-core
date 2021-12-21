SUMMARY = "Cryptographic library for Python"
DESCRIPTION = "PyCryptodome is a self-contained Python package of low-level\
 cryptographic primitives."
HOMEPAGE = "http://www.pycryptodome.org"
LICENSE = "PD & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=accfa6aeaceb3ba96676edf18e78302c"

SRC_URI[md5sum] = "04765bec00306c1051988b672cdbaaea"
SRC_URI[sha256sum] = "3e2e3a06580c5f190df843cdb90ea28d61099cf4924334d5297a995de68e4673"

inherit pypi setuptools

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-math \
"

RDEPENDS_${PN}-tests += " \
    ${PYTHON_PN}-unittest \
"

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests += " \
    ${PYTHON_SITEPACKAGES_DIR}/Crypto/SelfTest/ \
    ${PYTHON_SITEPACKAGES_DIR}/Crypto/SelfTest/__pycache__/ \
"

BBCLASSEXTEND = "native nativesdk"
include ${PYTHON_PN}-package-split.inc

PROVIDES += "${PYTHON_PN}-pycrypto"
RPROVIDES_${PN} += "${PYTHON_PN}-pycrypto"
RCONFLICTS_${PN} = "${PYTHON_PN}-pycrypto"
RREPLACES_${PN} = "${PYTHON_PN}-pycrypto"
