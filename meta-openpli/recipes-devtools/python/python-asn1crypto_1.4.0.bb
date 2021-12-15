DESCRIPTION = "A fast, pure Python library for parsing and serializing ASN.1 structures"
HOMEPAGE = "https://github.com/wbond/asn1crypto"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7439e38f5e04ff62fae436184786b7ca"

PYPI_PACKAGE = "asn1crypto"

SRC_URI[md5sum] = "00bf5b72d37778e62cc73b1d8909ae27"
SRC_URI[sha256sum] = "f4f6e119474e58e04a2b1af817eb585b4fd72bdd89b998624712b5c99be7641c"

inherit pypi setuptools

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_VER}-codecs \
    ${PYTHON_VER}-crypt \
    ${PYTHON_VER}-ctypes \
    ${PYTHON_VER}-datetime \
    ${PYTHON_VER}-io \
    ${PYTHON_VER}-netclient \
    ${PYTHON_VER}-subprocess \
    ${PYTHON_VER}-textutils \ 
"

BBCLASSEXTEND = "native nativesdk"
include ${PYTHON_VER}-package-split.inc
