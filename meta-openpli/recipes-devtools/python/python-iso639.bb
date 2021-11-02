SUMMARY  = "Python library for the ISO 639 standard"
HOMEPAGE = "https://github.com/noumar/iso639"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4f0bb71e3b4a29e013aa44d05f7cd085"

inherit setuptools gitpkgv

PV = "0.4.5+git${SRCPV}"
PKGV = "0.4.5+git${GITPKGV}"

SRC_URI = "git://github.com/noumar/iso639.git"
S = "${WORKDIR}/git"
