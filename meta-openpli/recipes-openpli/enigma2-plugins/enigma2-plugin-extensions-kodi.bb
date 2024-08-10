DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools gitpkgv python3-compileall

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS:${PN} = "enigma2-plugin-extensions-subssupport kodi"

RDEPENDS:${PN} = "${@bb.utils.contains_any('MACHINE', 'ustym4kpro ustym4ks2ottx', 'kodi', 'stb-kodi', d)} kodi-addons-meta kodi-addon-inputstream-adaptive-omega nspr nss"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/OpenPLi/kodiext.git;protocol=https;branch=main \
	file://advancedsettings.xml \
	file://advancedsettings-empty.xml \
"

S = "${WORKDIR}/git"

PV = "20+git${SRCPV}"
PKGV = "20+git${GITPKGV}"

FILES:${PN} = " \
    ${libdir}/enigma2/python/Plugins/Extensions/Kodi \
    ${bindir}/kodiext \
    ${datadir}/kodi/system \
    "

do_install:append() {
	install -d ${D}${datadir}/kodi/system
	if ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'false', 'true', d)}; then
		install -m 0755 ${WORKDIR}/advancedsettings.xml ${D}${datadir}/kodi/system
	else
		install -m 0755 ${WORKDIR}/advancedsettings-empty.xml ${D}${datadir}/kodi/system/advancedsettings.xml
	fi
}
