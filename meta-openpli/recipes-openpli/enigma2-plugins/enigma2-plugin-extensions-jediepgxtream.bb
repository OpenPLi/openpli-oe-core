DESCRIPTION = "Jedi EPG XStream (Add EPG to IPTV channels)"
MAINTAINER = "kiddac"
require conf/license/license-gplv2.inc
HOMEPAGE = "https://github.com/kiddac/Jedi-EPG-XStream"

inherit gitpkgv allarch python3-compileall

SRCREV="${AUTOREV}"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

SRC_URI = "git://github.com/kiddac/Jedi-EPG-XStream.git;protocol=https;branch=main \
           file://get-rid-of-fuzzywuzzy.patch \
           "

RDEPENDS:${PN} = "python3-requests python3-difflib  python3-thefuzz"

S = "${WORKDIR}/git"

FILES:${PN} = " ${sysconfdir}/enigma2/jediepgxtream/* \
                ${libdir}/enigma2/python/Plugins/Extensions/JediEPGXtream/*"

do_install () {
	install -d ${D}/${sysconfdir}/enigma2/jediepgxtream
	install -d ${D}/${libdir}/enigma2/python/Plugins/Extensions/JediEPGXtream
	cp -rf ${S}/JediEPGXtream//etc/enigma2/jediepgxtream/* ${D}/${sysconfdir}/enigma2/jediepgxtream/
	# rm -f ${S}/JediEPGXtream/usr/lib/enigma2/python/Plugins/Extensions/JediEPGXtream/locale/updateallpo.sh
	cp -rf ${S}/JediEPGXtream/usr/lib/enigma2/python/Plugins/Extensions/JediEPGXtream/* ${D}/${libdir}/enigma2/python/Plugins/Extensions/JediEPGXtream/
}

pkg_preinst:${PN} () {
#!/bin/sh
rm -rf /usr/lib/enigma2/python/Plugins/Extensions/JediEPGXtream > /dev/null 2>&1
rm -rf /etc/epgimport/*jex*.* > /dev/null 2>&1
}

pkg_postrm:${PN} () {
#!/bin/sh
rm -rf /usr/lib/enigma2/python/Plugins/Extensions/JediEPGXtream > /dev/null 2>&1
rm -rf /etc/epgimport/*jex*.* > /dev/null 2>&1
echo "Restart GUI to finish uninstall!"
}
