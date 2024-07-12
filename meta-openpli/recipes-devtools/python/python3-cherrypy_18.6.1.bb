SUMMARY = "CherryPy is a pythonic, object-oriented HTTP framework"
HOMEPAGE = "https://cheroot.cherrypy.org/"
SECTION = "devel/python"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=a8cbc5da4e6892b15a972a0b18622b2b"
DEPENDS += "python3-setuptools-scm-native"
RDEPENDS:${PN} = "python3-jaraco.collections python3-jaraco.classes python3-jaraco.text python3-jaraco.functools python3-zc.lockfile python3-tempora"

SRC_URI = "https://files.pythonhosted.org/packages/c6/0d/f6acfd12f098b9f05b9146b79b5a3fad02f4047a7831b5f5c9ee3fe54d56/CherryPy-${PV}.tar.gz"
SRC_URI[md5sum] = "01dec1a7164faf2406a3a03bf56aef80"
SRC_URI[sha256sum] = "f33e87286e7b3e309e04e7225d8e49382d9d7773e6092241d7f613893c563495"

S = "${WORKDIR}/CherryPy-${PV}"

inherit setuptools3

include python3-package-split.inc
