SUMMARY = "Streamlink[-27] is a fork of the Streamlink project which continues to support python 2.7"
DESCRIPTION = "The project extension [-27] indicates that this project continues to support python 2.7, \
                as the streamlink project has discontinued python 2.7 support as of version 1.7.0"
HOMEPAGE = "https://billy2011.github.io/streamlink-27"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7c0be52291b7252b878da806d185b1d1"

RDEPENDS_${PN} = "${PYTHON_VER}-core \
    ${PYTHON_VER}-backports-functools-lru-cache \
    ${PYTHON_VER}-backports-shutil-get-terminal-size \
    ${PYTHON_VER}-backports-shutil-which \
    ${PYTHON_VER}-ctypes \
    ${PYTHON_VER}-futures \
    ${PYTHON_VER}-isodate \
    ${PYTHON_VER}-lxml \
    ${PYTHON_VER}-misc \
    ${PYTHON_VER}-pkgutil \
    ${PYTHON_VER}-pycryptodome \
    ${PYTHON_VER}-pycountry \
    ${PYTHON_VER}-pysocks \
    ${PYTHON_VER}-requests \
    ${PYTHON_VER}-shell \
    ${PYTHON_VER}-singledispatch \
    ${PYTHON_VER}-subprocess \
    ${PYTHON_VER}-websocket-client \
    ${PYTHON_VER}-youtube-dl \
    "

inherit gittag setuptools ${PYTHON_VER}-dir

# SRCREV = "${AUTOREV}"

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/Billy2011/streamlink-27;branch=release-2.27.0.0;protocol=https"

FILESEXTRAPATHS_prepend := "${THISDIR}/streamlink-27:"

SRC_URI += " \
    file://0001-added-files.patch \
    "
S = "${WORKDIR}/git"

do_install_append() {
    rm -rf ${D}${bindir}
    rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/streamlink_cli
}

PACKAGES = "${PN}-src ${PN}"

FILES_${PN} = " \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/*.pyo \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/*/*.pyo \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/*/*/*.pyo \
    "

FILES_${PN}-src += " \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink-*.egg-info/* \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/plugins/.removed \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/streamlink/*/*/*.py \
    "
