SUMMARY = "Provides access to Linux Wireless Extensions"
HOMEPAGE = "http://pythonwifi.wikispot.org/"
SECTION = "devel/python"
LICENSE = "LGPL-2.0-or-later & GPL-2.0-or-later"
LICENSE:${PN}-examples = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README;md5=54307cbab01c3aad9adf7605132bcf31"

inherit gitpkgv setuptools3

PV = "0.7.0+git${SRCPV}"
PKGV = "0.7.0+git${GITPKGV}"
PR = "r2"

RDEPENDS:${PN} = "python3-ctypes python3-datetime python3-six"

SRC_URI = "git://github.com/athoik/pythonwifi.git;branch=master;protocol=https \
           file://replace-distutils-with-setuptools.patch \
	"
SRCREV ?= "${AUTOREV}"

S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}${docdir}/${PN}
    mv ${D}${prefix}/README ${D}${docdir}/${PN}
    mv ${D}${prefix}/docs/* ${D}${docdir}/${PN}
    mv ${D}${prefix}/man/* ${D}${docdir}/${PN}
    rmdir ${D}${prefix}/docs
    rmdir ${D}${prefix}/man
    install -d ${D}${sbindir}
    mv ${D}${prefix}/examples/* ${D}${sbindir}
    rmdir ${D}${prefix}/examples
}

PACKAGES =+ "${PN}-examples"

FILES:${PN}-examples = "${sbindir}"

include python3-package-split.inc
