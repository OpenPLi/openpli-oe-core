SUMMARY = "Python SNMP Toolkit (Speed Enhanced)"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README;beginline=13;endline=14;md5=fe0c3d0cab1e25fcec2777b0a8b8e4bf"

RDEPENDS_${PN} = "${PYTHON_PN}-core"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/Hains/python3-pysnmp-se.git;branch=main;protocol=http"

S = "${WORKDIR}/git"

inherit distutils3

include ${PYTHON_PN}-package-split.inc
