SUMMARY = "Module for manipulating ID3 (v1 + v2) tags in Python"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "python3"
RDEPENDS:${PN} = "python3-shell"

SRC_URI = "https://files.pythonhosted.org/packages/f3/d9/2232a4cb9a98e2d2501f7e58d193bc49c956ef23756d7423ba1bd87e386d/mutagen-${PV}.tar.gz"

SRC_URI[md5sum] = "76e1f7fc2386813c0ff2716695fb06e1"
SRC_URI[sha256sum] = "6397602efb3c2d7baebd2166ed85731ae1c1d475abca22090b7141ff5034b3e1"

S = "${WORKDIR}/mutagen-${PV}"

inherit distutils3 setuptools3

include python3-package-split.inc
