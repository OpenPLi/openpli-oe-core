DESCRIPTION = "Tool to create bouquets based on playlists from github.com/iptv-org"
MAINTAINER = "Huevos"
HOMEPAGE = "https://github.com/Huevos/iptv-org-playlists-plugin-for-enigma2"

inherit gitpkgv allarch gettext python3-compileall

require conf/license/license-gplv2.inc

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/Huevos/iptv-org-playlists-plugin-for-enigma2.git;protocol=https;branch=master"

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-requests \
    "

S = "${WORKDIR}/git"

FILES:${PN} = "${pluginpath}/"

pluginpath = "/usr/lib/enigma2/python/Plugins/Extensions/iptv-org-playlists"

do_install() {
	install -d ${D}${pluginpath}
	cp -r ${S}/src/* ${D}${pluginpath}/
}

