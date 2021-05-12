SUMMARY  = "Websocket client for Python"
HOMEPAGE = "https://github.com/websocket-client/websocket-client"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c96ca6c1de8adc025adfada81d06fba5"

inherit pypi setuptools

PYPI_PACKAGE = "websocket_client"

PV = "0.59.0"
PKGV = "0.59.0"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-backports-ssl \
"

SRC_URI = "https://github.com/websocket-client/websocket-client/archive/refs/tags/v0.59.0.tar.gz"
SRC_URI[md5sum] = "cca7963ae0fa803e7b33aafa20d03796"
SRC_URI[sha256sum] = "c9eebd9b70e3f0326ed586ba5e0f52be4d49782e48fa737298ae4f5fdaf8db92"

S = "${WORKDIR}/websocket-client-0.59.0"
