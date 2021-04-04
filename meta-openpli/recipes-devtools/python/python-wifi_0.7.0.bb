SUMMARY = "Provides access to Linux Wireless Extensions"
HOMEPAGE = "https://github.com/Opvolger/pythonwifi"
SECTION = "devel/python"
LICENSE = "LGPLv2+ & GPLv2+"
LICENSE_${PN}-examples = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=54307cbab01c3aad9adf7605132bcf31 \
					file://examples/iwconfig.py;md5=631f365ecfd50d13a70f15b38689ce4d \
					file://pythonwifi/iwlibs.py;md5=a648a9858e3e6973c53dc77944571649 \
					"

RDEPENDS_${PN} = "python-ctypes python-datetime"
PR = "r1"

SRC_URI = "https://github.com/Opvolger/pythonwifi/releases/download/0.7.0/python-wifi-0.7.0.tar.bz2"

SRC_URI[md5sum] = "2d7f2bab7345a2034c976096e31cc2ff"
SRC_URI[sha256sum] = "a18699739f07444b1781d4731286ac85c8c35f98ca1166cde2d9f91366bbdc76"

inherit setuptools

do_install_append() {
		install -d ${D}${docdir}/${PN}
		mv ${D}${datadir}/README ${D}${docdir}/${PN}
		mv ${D}${datadir}/docs/* ${D}${docdir}/${PN}
		rmdir ${D}${datadir}/docs
		install -d ${D}${sbindir}
		mv ${D}${datadir}/examples/* ${D}${sbindir}
		rmdir ${D}${datadir}/examples
}

PACKAGES =+ "${PN}-examples"

FILES_${PN}-examples = "${sbindir}"

include python-package-split.inc
