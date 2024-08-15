SUMMARY = "High-level Twisted HTTP Client API"
DESCRIPTION = "Provides simple, higher level API for making HTTP requests when using Twisted."
HOMEPAGE = "https://github.com/twisted/treq"
AUTHOR = "David Reid <dreid@dreid.org>"
MAINTAINER = "Tom Most <twm@freecog.net>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5dd67fd17479587f7093769d95ef186"
SECTION = "devel/python"

S = "${WORKDIR}/${PN}-${PV}"

DEPENDS += "python3-incremental-native"

RDEPENDS:${PN} += "python3-attrs python3-hyperlink python3-incremental python3-requests python3-twisted"

RDEPENDS:${PN}-dbg = "${PN}"
FILES:${PN}-dbg = " \
    ${PYTHON_SITEPACKAGES_DIR}/${PN}-*.egg-info \
    ${PYTHON_SITEPACKAGES_DIR}/${PN}/test \
"

PYPI_PACKAGE = "treq"

SRC_URI[md5sum] = "bdb28bd00f10abaf367f074b9ada300d"
SRC_URI[sha256sum] = "0914ff929fd1632ce16797235260f8bc19d20ff7c459c1deabd65b8c68cbeac5"

inherit pypi setuptools3

include python3-package-split.inc

do_compile[network] = "1"
