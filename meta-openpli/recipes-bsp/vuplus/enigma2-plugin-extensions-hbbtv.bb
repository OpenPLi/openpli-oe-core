inherit gitpkgv

DESCRIPTION = "VU+ HBBTV plugin"

LICENSE			= "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV			= "59df7d4b4fc7d1740f30d36290553972c4a3a652"
PV				= "experimental-git${SRCPV}"
PKGV			= "experimental-git${GITPKGV}"
PR				= "r11"
BRANCH			= "vuplus_experimental"
RDEPENDS_${PN}	= "vuplus-opera-browser vuplus-hbbtv-dumpait"
FILES_${PN}		= "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV/*"
PACKAGES		= "${PN}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   file://hbbtv-add-abort.patch;striplevel=1 \
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
