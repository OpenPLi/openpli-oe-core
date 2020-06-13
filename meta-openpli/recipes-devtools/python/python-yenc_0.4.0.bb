DESCRIPTION = "yEnc module for Python"
SECTION = "devel/python"
DEPENDS = "python"
PRIORITY = "optional"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=5858eb949cc6db7a2879a5eb38b3423a"

SRCNAME = "yenc"
PR = "ml1"

SRC_URI = "http://www.golug.it/pub/yenc/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

SRC_URI[md5sum] = "f8bd9eff020c0352fdd4c25c20900007"
SRC_URI[sha256sum] = "075f6c4e4f43b7c6dafac579eabb17287b62d80e9147cbea0b046bc3ee8edd2f"

include python-package-split.inc
