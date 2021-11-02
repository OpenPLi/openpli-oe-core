SUMMARY  = "Standalone ISO 3166 Country definitions"
HOMEPAGE = "https://github.com/deactivated/python-iso3166"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5e2f4edc7e7408a82e4a1d05f229b695"

inherit setuptools gitpkgv

PV = "1.0.1+git${SRCPV}"
PKGV = "1.0.1+git${GITPKGV}"

SRC_URI = "git://github.com/deactivated/python-iso3166.git"
S = "${WORKDIR}/git"
