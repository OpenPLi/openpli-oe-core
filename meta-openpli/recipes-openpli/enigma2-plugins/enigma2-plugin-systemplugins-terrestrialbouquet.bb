# enigma2-plugin-systemplugins-terrestrialbouquet.bb

DESCRIPTION = "Tool to create a terrestrial bouquet according to logical channel numbers"
MAINTAINER = "Huevos"
HOMEPAGE = "https://github.com/Huevos/TerrestrialBouquet"

inherit gitpkgv allarch gettext python3-compileall

require conf/license/license-gplv2.inc

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/Huevos/TerrestrialBouquet.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

pluginpath = "/usr/lib/enigma2/python/Plugins/SystemPlugins/TerrestrialBouquet"

FILES_${PN} = "${pluginpath}/"

do_install() {
    install -d ${D}${pluginpath}
    cp -r ${S}/src/* ${D}${pluginpath}/
}
