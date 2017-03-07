SUMMARY = "Collection of enigma2 subtitles plugins"
HOMEPAGE = "https://github.com/mx3L/subssupport"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"

RDEPENDS_${PN} = "python-xmlrpc python-compression python-codecs python-zlib python-difflib unrar"

SRCREV = "7a9a809bd5938472181a69c8786842df50d689fa"
SRC_URI = "git://github.com/mx3L/subssupport;protocol=git;branch=master"

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/SubsSupport \
${localstatedir}/lib/subssupport"

inherit autotools-brokensep

do_install_append() {
    install -d ${D}${localstatedir}/lib/subssupport
}

