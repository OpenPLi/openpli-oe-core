SUMMARY = "websocket client for python"
DESCRIPTION = "\
websocket-client module is WebSocket client for python. \
This provide the low level APIs for WebSocket. All APIs \
are the synchronous functions."
HOMEPAGE = "https://github.com/websocket-client/websocket-client"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c96ca6c1de8adc025adfada81d06fba5"

SRC_URI[sha256sum] = "1315816c0acc508997eb3ae03b9d3ff619c9d12d544c9a9b553704b1cc4f6af5"

PYPI_PACKAGE = "websocket_client"

inherit pypi setuptools3

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-six \
"
