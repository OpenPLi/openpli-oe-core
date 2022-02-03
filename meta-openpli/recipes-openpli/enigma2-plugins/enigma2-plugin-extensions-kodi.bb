DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS_${PN} = "enigma2-plugin-extensions-subssupport"

RDEPENDS_${PN} = "kodi kodi-addons-meta"

SRCREV = "761ec559773857d27d8be5cccd2b7e84adb8f486"
SRC_URI = "git://github.com/oe-alliance/kodiext.git;protocol=https;branch=python3 \
	file://advancedsettings.xml \
"

S = "${WORKDIR}/git"

PV = "19+git${SRCPV}"
PKGV = "19+git${GITPKGV}"

do_install_append() {
	install -d ${D}${datadir}/kodi/system
	install -m 0755 ${WORKDIR}/advancedsettings.xml ${D}${datadir}/kodi/system
}

FILES_${PN} = " \
    ${libdir}/enigma2/python/Plugins/Extensions/Kodi \
    ${bindir}/kodiext \
    ${datadir}/kodi/system \
    "

inherit gitpkgv autotools
INSANE_SKIP += "file-deps"
