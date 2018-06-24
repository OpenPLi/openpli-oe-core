inherit gitpkgv autotools

DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS_${PN} = "enigma2-plugin-extensions-subssupport virtual/kodi"

SRC_URI = "git://github.com/mx3L/kodiext;protocol=git;branch=master \
	file://0001-make-transparant.patch \
	file://advancedsettings.xml \
"

S = "${WORKDIR}/git"

PV = "0.5+git${SRCPV}"
PKGV = "0.5+git${GITPKGV}"

FILES_${PN} = " \
    ${libdir}/enigma2/python/Plugins/Extensions/Kodi \
    ${bindir}/kodiext \
    ${datadir}/kodi/system \
    "

do_install_append() {
	install -d ${D}${datadir}/kodi/system
	install -m 0755 ${WORKDIR}/advancedsettings.xml ${D}${datadir}/kodi/system
}
