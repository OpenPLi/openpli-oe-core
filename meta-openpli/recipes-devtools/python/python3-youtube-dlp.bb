SUMMARY = "A feature-rich command-line audio/vidoe downloader"
DESCRIPTION = "yt-dlp is a feature-rich command-line audio/video downloader with support for thousands of sites \
The project is a fork of youtube-dl based on the now inactive youtube-dlc"
HOMEPAGE = "https://github.com/yt-dlp/yt-dlp"
SECTION = "devel/python"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7246f848faa4e9c9fc0ea91122d6e680"

DEPENDS = "libxml2 bash-completion python3-hatchling-native"

inherit python_flit_core python3-dir gittag

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/yt-dlp/yt-dlp.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "PYTHON=${PYTHON}"

do_compile:prepend() {
    cd ${S}
    oe_runmake lazy-extractors completions/bash/yt-dlp
}

RDEPENDS:${PN} = " \
    python3-email \
    python3-gdata-python3 \
    python3-unixadmin \
    python3-ctypes \
    python3-html \
    "

RDEPENDS:{PN}-src = "${PN}"
FILES:${PN}-src = " \
    ${datadir}/etc/* \
    "

FILES:${PN} += "${sysconfdir} ${datadir}"
