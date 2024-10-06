DESCRIPTION = "RapidFuzz provides libraries for fuzzy string matching in various programming languages."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/README.md;md5=cd30fe688968c142c6223b363c0bcd3b"

DEPENDS = "python3-cython-native"

SRC_URI="git://github.com/rapidfuzz/rapidfuzz;branch=main;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv pkgconfig scikit-build-core

PV = "3.10.0.+git${SRCPV}"
PKGV = "3.10.0+git${GITPKGV}"

do_install:append() {
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/*.dist-info
}

FILES:${PN} = "${PYTHON_SITEPACKAGES_DIR}"
FILES:${PN}-src = "${PYTHON_SITEPACKAGES_DIR}/${PN}/*.py \
                   ${PYTHON_SITEPACKAGES_DIR}/${PN}/*/*.py \
                   "
