SUMMARY = "Collection of enigma2 subtitles plugins"
HOMEPAGE = "https://github.com/mx3L/subssupport"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

RDEPENDS_${PN} = "${PYTHON_VER}-requests ${PYTHON_VER}-xmlrpc ${PYTHON_VER}-compression ${PYTHON_VER}-codecs ${PYTHON_VER}-zlib ${PYTHON_VER}-difflib unrar"

inherit autotools-brokensep gettext gittag

SRC_URI = "git://github.com/mx3L/subssupport;protocol=https;branch=master"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/SubsSupport \
${localstatedir}/lib/subssupport"

do_install_append() {
    install -d ${D}${localstatedir}/lib/subssupport
}

