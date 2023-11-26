SUMMARY = "A pure Python DAAP client implementation"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fbc093901857fcd118f065f900982c24"

RDEPENDS:${PN} = "${PYTHON_PN}-compression"

inherit gitpkgv setuptools3

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/abdelgmartinezl/PythonDaap;protocol=https;branch=master \
           file://python-daap.patch \
           file://The-member-ob_type-is-not-present-in-the-pcapobject.patch \
           file://use-setuptools-instead-of-distutils.patch \
"

S = "${WORKDIR}/git"

include python3-package-split.inc
