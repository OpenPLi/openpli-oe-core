SUMMARY = "IPTV bouquet maker"
DESCRIPTION = "Bouquet Maker Xtream"
HOMEPAGE = "https://github.com/kiddac/Bouquet_Maker_Xtream"
MAINTAINER = "kiddac"
PRIORITY = "optional"
require conf/license/license-gplv2.inc

SRCREV="${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

inherit gittag allarch

RDEPENDS_${PN} = "${PYTHON_PN} ${PYTHON_PN}-requests ${PYTHON_PN}-pillow"

SRC_URI = "git://github.com/kiddac/Bouquet_Maker_Xtream.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

FILES:${PN} = " ${libdir}/enigma2/python/Plugins/Extensions/BouquetMakerXtream \
                ${libdir}/enigma2/python/Components/Renderer \
                ${sysconfdir}/enigma2/bouquetmakerxtream"

CONFFILES:${PN} = "${sysconfdir}/enigma2/bouquetmakerxtream/playlists.txt"

do_install() {
    install -d ${D}${sysconfdir}/enigma2/bouquetmakerxtream
    install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/BouquetMakerXtream
    install -d ${D}${libdir}/enigma2/python/Components/Renderer ${D}${libdir}/enigma2/python/Components/Renderer
    cp -rf ${S}/BouquetMakerXtream/usr/lib/enigma2/python/Plugins/Extensions/BouquetMakerXtream/* ${D}${libdir}/enigma2/python/Plugins/Extensions/BouquetMakerXtream
    cp -rf ${S}/BouquetMakerXtream/usr/lib/enigma2/python/Components/Renderer/* ${D}${libdir}/enigma2/python/Components/Renderer
}

