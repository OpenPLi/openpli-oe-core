DESCRIPTION = "Jedi Maker Xtream (Enigma2 IPTV Bouquet Creator)"
MAINTAINER = "kiddac"
require conf/license/license-gplv2.inc
HOMEPAGE = "https://github.com/kiddac/Jedi_Maker_Xtream"

inherit gitpkgv allarch python3-compileall

SRCREV="${AUTOREV}"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

SRC_URI = "git://github.com/WanWizard/Jedi_Maker_Xtream.git;protocol=https"

S = "${WORKDIR}/git"

FILES:${PN} = " ${sysconfdir}/enigma2/jediplaylists/* \
                ${libdir}/enigma2/python/Plugins/Extensions/JediMakerXtream/*"

do_install () {
	install -d ${D}/${sysconfdir}/enigma2/jediplaylists
	install -d ${D}/${libdir}/enigma2/python/Plugins/Extensions/JediMakerXtream
	cp -rf ${S}/JediMakerXtream//etc/enigma2/jediplaylists/* ${D}/${sysconfdir}/enigma2/jediplaylists/
	rm -f ${S}/JediMakerXtream/usr/lib/enigma2/python/Plugins/Extensions/JediMakerXtream/locale/updateallpo.sh
	cp -rf ${S}/JediMakerXtream/usr/lib/enigma2/python/Plugins/Extensions/JediMakerXtream/* ${D}/${libdir}/enigma2/python/Plugins/Extensions/JediMakerXtream/
}

pkg_preinst_${PN} () {
#!/bin/sh
rm -rf /etc/enigma2/jediplaylists/playlist_all.json > /dev/null 2>&1
rm -rf /usr/lib/enigma2/python/Plugins/Extensions/JediMakerXtream > /dev/null 2>&1
rm -rf /etc/enigma2/*jmx*.* > /dev/null 2>&1
rm -rf /etc/epgimport/*jmx*.* > /dev/null 2>&1
sed -i '/jmx/d' /etc/enigma2/bouquets.tv
}

pkg_postrm_${PN} () {
#!/bin/sh
rm -rf /etc/enigma2/jediplaylists/playlist_all.json > /dev/null 2>&1
rm -rf /usr/lib/enigma2/python/Plugins/Extensions/JediMakerXtream > /dev/null 2>&1
rm -rf /etc/enigma2/*jmx*.* > /dev/null 2>&1
rm -rf /etc/epgimport/*jmx*.* > /dev/null 2>&1
sed -i '/jmx/d' /etc/enigma2/bouquets.tv
echo "Restart GUI to finish uninstall!"
}
