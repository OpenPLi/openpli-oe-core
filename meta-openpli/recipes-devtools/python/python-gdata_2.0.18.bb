SUMMARY = "Google Data APIs Python Client Library"
HOMEPAGE = "http://code.google.com/p/gdata-python-client/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.txt;beginline=1;endline=13;md5=7a713fc5eed20ac1904c2efe0b816a33"
RDEPENDS_${PN} = "python-elementtree"
RDEPENDS_${PN}-tests = "${PN}"

SRC_URI = "http://gdata-python-client.googlecode.com/files/gdata-${PV}.tar.gz;name=archive"
SRC_URI[archive.md5sum] = "13b6e6dd8f9e3e9a8e005e05a8329408"
SRC_URI[archive.sha256sum] = "56e7d22de819c22b13ceb0fe1869729b4287f89ebbd4bb55380d7bcf61a1fdb6"

S = "${WORKDIR}/gdata-${PV}"

inherit distutils

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests = "${libdir}/${PYTHON_DIR}/site-packages/gdata/test*"
FILES_${PN} += "${datadir}"
