SUMMARY  = "Websocket client for Python"
HOMEPAGE = "https://github.com/websocket-client/websocket-client"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c96ca6c1de8adc025adfada81d06fba5"

inherit pypi setuptools gitpkgv

PV = "0.58.0+git${SRCPV}"
PKGV = "0.58.0+git${GITPKGV}"

SRC_URI = "git://github.com/websocket-client/websocket-client.git"
S = "${WORKDIR}/git"
