DESCRIPTION = "Vuplus Specific plugins"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"

DEPENDS = " python-native vuplus-opera-browser-util"
RDEPENDS = "vuplus-opera-browser-${MACHINE} "

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "5e7d2b26c7b0f73bf57aeb9f137685485387714d"
inherit gitpkgv
 
PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
PR = "r2"
BRANCH = "vuplus_experimental"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   file://vuplus-opera-browser-plugin.patch;striplevel=1 \
"

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/HbbTV/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

FILES_enigma2-plugin-extensions-hbbtv = "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV"

PACKAGES = "enigma2-plugin-extensions-hbbtv"

PROVIDES="${PACKAGES}"

