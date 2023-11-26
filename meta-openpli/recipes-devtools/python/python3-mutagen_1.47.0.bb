SUMMARY = "Module for manipulating ID3 (v1 + v2) tags in Python"
SECTION = "devel/python"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "python3"
RDEPENDS:${PN} = "python3-shell"

SRC_URI = "https://files.pythonhosted.org/packages/81/e6/64bc71b74eef4b68e61eb921dcf72dabd9e4ec4af1e11891bbd312ccbb77/mutagen-${PV}.tar.gz"

SRC_URI[md5sum] = "aa2d0d73e71c5daa1a730f7b94272357"
SRC_URI[sha256sum] = "719fadef0a978c31b4cf3c956261b3c58b6948b32023078a2117b1de09f0fc99"

S = "${WORKDIR}/mutagen-${PV}"

inherit setuptools3

include python3-package-split.inc
