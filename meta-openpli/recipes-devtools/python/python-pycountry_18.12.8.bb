UMMARY = "ISO country, subdivision, language, currency and script definitions and their translations"
HOMEPAGE = "https://github.com/flyingcircusio/pycountry"
AUTHOR = "Christian Theune <ct@flyingcircus.io>"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2e3f24d82bf0f89a5a3be6801b999bf1"

include python-package-split.inc

inherit setuptools pypi

SRC_URI[sha256sum] = "8ec4020b2b15cd410893d573820d42ee12fe50365332e58c0975c953b60a16de"

do_install_append() {
    rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/pycountry/locales*
}
