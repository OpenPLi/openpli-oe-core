inherit gitpkgv

DESCRIPTION = "VU+ HBBTV plugin"

LICENSE			= "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV			= "6e2ef83a9d63faa57c7266d7331b0ca9b9f4c9f9"
PV				= "experimental-git${SRCPV}"
PKGV			= "experimental-git${GITPKGV}"
PR				= "r8"
BRANCH			= "vuplus_experimental"
DEPENDS			= "vuplus-opera-browser-util vuplus-opera-dumpait"
PROVIDES		= "vuplus-opera-browser-plugin"
RDEPENDS_${PN}	= "vuplus-opera-browser-util vuplus-opera-dumpait"
FILES_${PN}		= "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/*"
PACKAGES		= "${PN}"
PACKAGE_ARCH	= "${MACHINE_ARCH}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   file://vuplus-opera-browser-plugin_20130105.patch;striplevel=1 \
	   file://hbbtv-nocrash.patch;striplevel=1 \
	   file://vuplus-opera-browser-plugin-append_20130429.patch;striplevel=1 \
	   file://aitreader.py \
	  "

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/HbbTV/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${WORKDIR}/aitreader.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/HbbTV/keymap.xml ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/locale
	cp -av ${S}/lib/python/Plugins/Extensions/HbbTV/locale/*.po ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/locale

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}
