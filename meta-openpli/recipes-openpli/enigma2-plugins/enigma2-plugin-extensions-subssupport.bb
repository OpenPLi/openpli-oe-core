SUMMARY = "Collection of enigma2 subtitles plugins"
HOMEPAGE = "https://github.com/mx3L/subssupport"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

RDEPENDS_${PN} = "${PYTHON_PN}-requests ${PYTHON_PN}-xmlrpc ${PYTHON_PN}-compression ${PYTHON_PN}-codecs ${PYTHON_PN}-difflib unrar"

inherit autotools-brokensep gettext gittag ${PYTHON_PN}native

SRC_URI = "git://github.com/oe-mirrors/subssupport;protocol=https;branch=master"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/SubsSupport \
${localstatedir}/lib/subssupport"

FILES_${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/SubsSupport/*.py \
    ${libdir}/enigma2/python/Plugins/Extensions/SubsSupport/*/*.py \
    ${libdir}/enigma2/python/Plugins/Extensions/SubsSupport/*/*/*.py"

do_install_append() {
    install -d ${D}${localstatedir}/lib/subssupport
}

# Just a quick hack to "compile" the python parts.
do_compile_append() {
    python3 -O -m compileall ${S}
}
