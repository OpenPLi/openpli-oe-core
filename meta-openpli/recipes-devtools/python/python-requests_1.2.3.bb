DESCRIPTION = "Requests: HTTP for Humans"
HOMEPAGE = "http://docs.python-requests.org/en/latest/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
PACKAGE_ARCH = "all"

RDEPENDS_${PN} = "python \
	python-codecs \
	python-compression \
	python-io \
	python-json \
	python-zlib \
	"

PR = "r3"

SRC_URI = "git://github.com/kennethreitz/requests;protocol=git"

S = "${WORKDIR}/git/"

SRCREV = "3bb13f8fbb9d4ed1a20bd33495cdc087eb062ca0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=c858703330162aa799141655a10fe120"

inherit setuptools

do_install_append() {
    rm -f ${D}${libdir}/${PYTHON_DIR}/site-packages/site.py*
}

PACKAGES =+ "${PN}-src"

FILES_${PN}-src = "\
	${libdir}/${PYTHON_DIR}/site-packages/requests/requests-${PV}-*.egg-info/* \
	${libdir}/${PYTHON_DIR}/site-packages/requests/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/requests/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/requests/*/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/requests/*/*/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/requests/*/*/*/*/*.py \
	"
