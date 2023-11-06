SUMMARY = "Module for manipulating ID3 (v1 + v2) tags in Python"
SECTION = "devel/python"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "python3"
RDEPENDS:${PN} = "python3-shell"

SRC_URI = "https://files.pythonhosted.org/packages/b1/54/d1760a363d0fe345528e37782f6c18123b0e99e8ea755022fd51f1ecd0f9/mutagen-${PV}.tar.gz"

SRC_URI[md5sum] = "648c9f38b8ef46ffb0d5135524650b87"
SRC_URI[sha256sum] = "6e5f8ba84836b99fe60be5fb27f84be4ad919bbb6b49caa6ae81e70584b55e58"

S = "${WORKDIR}/mutagen-${PV}"

inherit setuptools3 setuptools3

include python3-package-split.inc
