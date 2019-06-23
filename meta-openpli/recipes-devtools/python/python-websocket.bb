SUMMARY  = "Websocket client for Python"
HOMEPAGE = "https://github.com/websocket-client/websocket-client"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c4c4a98fbc4836b81c8c64d6ecb01fc1"

inherit setuptools gitpkgv

PV = "0.44.0+git${SRCPV}"
PKGV = "0.44.0+git${GITPKGV}"

SRC_URI = "git://github.com/websocket-client/websocket-client.git"
S = "${WORKDIR}/git"
