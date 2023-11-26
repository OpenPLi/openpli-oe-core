SUMMARY = "Versioning It with your Version In Git"
HOMEPAGE = "https://github.com/jwodder/versioningit"
AUTHOR = "John Thorvald Wodder II <versioningit@varonathe.org>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f50f611f98ab86be42339ced21a10fdc"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

RDEPENDS:${PN} += "${PYTHON_PN}-packaging ${PYTHON_PN}-tomli"

SRC_URI[sha256sum] = "0e58242d7abda61ae63596a5494ae9ed631ac85d8bb3cc8e176e3253ca4bcbb5"

PYPI_PACKAGE = "versioningit"

inherit pypi python_setuptools_build_meta

require python3-package-split.inc

FILES:${PN}-src += "${bindir}/versioningit"

BBCLASSEXTEND = "native"
