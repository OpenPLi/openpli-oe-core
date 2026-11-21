SUMMARY = "IPTV Ministra stalker player by KiddaC"
HOMEPAGE = "https://github.com/kiddac/EStalker"
MAINTAINER = "kiddac"
require conf/license/license-gplv2.inc

RDEPENDS:${PN} = "python3 python3-multiprocessing python3-requests \
                  python3-imaging python3-pillow \
"

inherit gittag allarch python3-compileall

PV = "git"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/kiddac/EStalker.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

FILES:${PN} = " ${libdir}/enigma2/python/Components/Converter/* \
                ${libdir}/enigma2/python/Components/Renderer/* \
                ${libdir}/enigma2/python/Plugins/Extensions/EStalker/*"

do_patch[noexec] = "1"

do_configure[noexec] = "1"

do_compile[noexec] = "1"

do_install() {
install -d ${D}${libdir}/enigma2/python/Components/Converter
install -d ${D}${libdir}/enigma2/python/Components/Renderer
install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/EStalker
cp -rf ${S}/EStalker/usr/lib/enigma2/python/Components/Converter/*.py ${D}${libdir}/enigma2/python/Components/Converter/
cp -rf ${S}/EStalker/usr/lib/enigma2/python/Components/Renderer/*.py ${D}${libdir}/enigma2/python/Components/Renderer/
cp -rf ${S}/EStalker/usr/lib/enigma2/python/Plugins/Extensions/EStalker/* ${D}${libdir}/enigma2/python/Plugins/Extensions/EStalker/
}
