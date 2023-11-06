SUMMARY = "Provides access to Linux Wireless Extensions"
HOMEPAGE = "http://pythonwifi.wikispot.org/"
SECTION = "devel/python"
LICENSE = "LGPL-2.0-or-later & GPL-2.0-or-later"
LICENSE:${PN}-examples = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README;md5=54307cbab01c3aad9adf7605132bcf31"

inherit gitpkgv

PV = "0.7.0+git${SRCPV}"
PKGV = "0.7.0+git${GITPKGV}"
PR = "r2"

RDEPENDS:${PN} = "python3-ctypes python3-datetime python3-six"

SRC_URI = "git://github.com/athoik/pythonwifi.git;branch=master;protocol=https"
SRCREV ?= "${AUTOREV}"

S = "${WORKDIR}/git"

inherit setuptools3

do_install:append() {
    install -d ${D}${docdir}/${PN}
    mv ${D}${datadir}/README ${D}${docdir}/${PN}
    mv ${D}${datadir}/docs/* ${D}${docdir}/${PN}
    rmdir ${D}${datadir}/docs
    install -d ${D}${sbindir}
    mv ${D}${datadir}/examples/* ${D}${sbindir}
    rmdir ${D}${datadir}/examples
}

PACKAGES =+ "${PN}-examples"

FILES:${PN}-examples = "${sbindir}"

include python3-package-split.inc
