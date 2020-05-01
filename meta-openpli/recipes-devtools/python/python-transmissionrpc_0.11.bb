DESCRIPTION = "Transmission RPC is a python module that can communicate with the bittorrent client Transmission through json-rpc"
HOMEPAGE = "http://bitbucket.org/blueluna/transmissionrpc/wiki/Home"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=584bf581d06c2ed41ccd154e1b06c5ab"
RDEPENDS_${PN} = "python-simplejson"

SRC_URI = "https://files.pythonhosted.org/packages/f5/f8/96a979b669a7219cb4299ea5512e1678ba7f59d91bd8a952c51405131768/transmissionrpc-${PV}.tar.gz"

S = "${WORKDIR}/transmissionrpc-${PV}"

SRC_URI[md5sum] = "b2f918593e509f0e66e2e643291b436d"
SRC_URI[sha256sum] = "ec43b460f9fde2faedbfa6d663ef495b3fd69df855a135eebe8f8a741c0dde60"

inherit setuptools

include python-package-split.inc
