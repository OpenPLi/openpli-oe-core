SUMMARY = "Module for manipulating ID3 (v1 + v2) tags in Python"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SRCNAME = "mutagen"
PR = "r1"

DEPENDS = "python"
RDEPENDS_${PN} = "python-shell"

SRC_URI = "https://github.com/quodlibet/mutagen/releases/download/release-${PV}/mutagen-${PV}.tar.gz \
		  "

SRC_URI[md5sum] = "c54443dfb8d4ac088eb2a0c4fd569772"
SRC_URI[sha256sum] = "23990f70ae678c7b8df3fd59e2adbefa5fe392c36da8c71d2254b21c6cd78766"

S = "${WORKDIR}/mutagen-${PV}"

inherit distutils

include python-package-split.inc
