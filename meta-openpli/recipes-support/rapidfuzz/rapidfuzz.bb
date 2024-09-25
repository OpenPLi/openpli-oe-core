DESCRIPTION = "RapidFuzz provides libraries for fuzzy string matching in various programming languages."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/README.md;md5=cd30fe688968c142c6223b363c0bcd3b"

DEPENDS = "scikit-build-core-native ninja-native python3-cython-native cmake-native"

SRC_URI="git://github.com/rapidfuzz/rapidfuzz;branch=main;protocol=https \
         file://remove-cmake-check.patch \
         "

S = "${WORKDIR}/git"

inherit gitpkgv pkgconfig python_flit_core

PV = "3.10.0.+git${SRCPV}"
PKGV = "3.10.0+git${GITPKGV}"

do_install:append() {
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/*.dist-info
}

FILES:${PN}-src = "${PYTHON_SITEPACKAGES_DIR}/${PN}/*.py \
                   ${PYTHON_SITEPACKAGES_DIR}/${PN}/*/*.py \
                   "
