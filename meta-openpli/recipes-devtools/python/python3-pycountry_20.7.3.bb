UMMARY = "ISO country, subdivision, language, currency and script definitions and their translations"
HOMEPAGE = "https://github.com/flyingcircusio/pycountry"
AUTHOR = "Christian Theune <ct@flyingcircus.io>"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2e3f24d82bf0f89a5a3be6801b999bf1"

include ${PYTHON_PN}-package-split.inc

inherit setuptools3 pypi

SRC_URI += "file://version.patch"
SRC_URI[sha256sum] = "81084a53d3454344c0292deebc20fcd0a1488c136d4900312cbd465cf552cb42"

do_install_append() {
    rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/pycountry/locales*
}
