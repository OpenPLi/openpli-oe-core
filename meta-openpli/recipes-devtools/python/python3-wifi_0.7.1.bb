SUMMARY = "Provides access to Linux Wireless Extensions"
HOMEPAGE = "http://pythonwifi.wikispot.org/"
SECTION = "devel/python"
LICENSE = "LGPLv2+ & GPLv2+"
LICENSE:${PN}-examples = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=54307cbab01c3aad9adf7605132bcf31"

PR = "r1"

RDEPENDS:${PN} = "python3-ctypes python3-datetime"

SRC_URI = " https://github.com/Opvolger/pythonwifi/releases/download/0.7.0/python-wifi-0.7.0.tar.bz2 \
			file://rename-tostring-to-tobytes.patch \
			file://dont-encode-ifname-to-bytes.patch \
			file://use-bytes-to-split.patch \
"

S = "${WORKDIR}/python-wifi-${PV}"

SRC_URI[md5sum] = "2d7f2bab7345a2034c976096e31cc2ff"
SRC_URI[sha256sum] = "a18699739f07444b1781d4731286ac85c8c35f98ca1166cde2d9f91366bbdc76"

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

include python-package-split.inc
