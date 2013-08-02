inherit gitpkgv

DESCRIPTION	 = "VU+ Zepping Mode Selection plugin"
LICENSE		 = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV		 = "8d6204fa8ce5d67ebc0d5dbae5c77cce481b503d"
PV		 = "experimental-git${SRCPV}"
PKGV		 = "experimental-git${GITPKGV}"
PR		 = "r0"
BRANCH		 = "vuplus_experimental"
DEPENDS		 = ""
PROVIDES	 = "enigma2-plugin-systemplugins-zappingmodeselection"
RDEPENDS_${PN}	 = ""
FILES_${PN}	 = "/usr/lib/enigma2/python/Plugins/SystemPlugins/ZappingModeSelection/*"
PACKAGES	 = "${PN}"
PACKAGE_ARCH	 = "${MACHINE_ARCH}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	  "

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/ZappingModeSelection
	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/ZappingModeSelection/*.py ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/ZappingModeSelection
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}


