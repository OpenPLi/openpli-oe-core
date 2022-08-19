DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools python3-compileall

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS_${PN} = "enigma2-plugin-extensions-subssupport kodi"

RDEPENDS_${PN} = "kodi kodi-addons-meta kodi-addon-inputstream-adaptive nspr nss"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/kueken/kodiext.git;protocol=https;branch=py3 \
	file://advancedsettings.xml \
	file://advancedsettings-empty.xml \
"

S = "${WORKDIR}/git"

PV = "19+git${SRCPV}"
PKGV = "19+git${GITPKGV}"

do_install_append() {
	install -d ${D}${datadir}/kodi/system
	if ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'false', 'true', d)}; then
		install -m 0755 ${WORKDIR}/advancedsettings.xml ${D}${datadir}/kodi/system
	else
		install -m 0755 ${WORKDIR}/advancedsettings-empty.xml ${D}${datadir}/kodi/system/advancedsettings.xml
	fi
}

FILES_${PN} = " \
    ${libdir}/enigma2/python/Plugins/Extensions/Kodi \
    ${bindir}/kodiext \
    ${datadir}/kodi/system \
    "

inherit gitpkgv autotools
INSANE_SKIP += "file-deps"
