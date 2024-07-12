SUMMARY = "Collection of enigma2 subtitles plugins"
HOMEPAGE = "https://github.com/mx3L/subssupport"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RDEPENDS:${PN} = "python3-requests python3-xmlrpc python3-compression python3-codecs python3-difflib python3-rarfile unrar"

inherit autotools-brokensep gettext gittag python3native python3-compileall

SRC_URI = "git://github.com/oe-mirrors/subssupport;protocol=https;branch=master"
SRC_URI:append = " file://0002-python3-hardlink.patch"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

FILES:${PN} = "${libdir}/enigma2/python/Plugins/Extensions/SubsSupport \
${localstatedir}/lib/subssupport"


do_install:append() {
    install -d ${D}${localstatedir}/lib/subssupport
}
