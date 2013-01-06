DESCRIPTION = "Vuplus Specific plugins"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"

DEPENDS = "vuplus-opera-browser-util"
RDEPENDS = "vuplus-opera-browser-util"

SRCREV = "6e2ef83a9d63faa57c7266d7331b0ca9b9f4c9f9"
inherit gitpkgv
 
PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
PR = "r4"
BRANCH = "vuplus_experimental"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   file://vuplus-opera-browser-plugin_20130105.patch;striplevel=1 \
	   file://aitreader.py \
	   file://dumpait \
"

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/HbbTV/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${WORKDIR}/aitreader.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0755 ${WORKDIR}/dumpait ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

FILES_enigma2-plugin-extensions-hbbtv = "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV"

PACKAGES = "enigma2-plugin-extensions-hbbtv"
PROVIDES="${PACKAGES}"
PACKAGE_ARCH := "${MACHINE_ARCH}"
