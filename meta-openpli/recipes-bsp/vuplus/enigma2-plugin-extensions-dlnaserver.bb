inherit gitpkgv

DESCRIPTION	 = "VU+ DLNA Server plugin"
LICENSE		 = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV		 = "8d6204fa8ce5d67ebc0d5dbae5c77cce481b503d"
PV		 = "experimental-git${SRCPV}"
PKGV		 = "experimental-git${GITPKGV}"
PR		 = "r0"
BRANCH		 = "vuplus_experimental"
DEPENDS		 = "minidlna"
PROVIDES	 = "enigma2-plugin-systemplugins-dlnaserver"
RDEPENDS_${PN}	 = "minidlna"
FILES_${PN}	 = "/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer/*"
PACKAGES	 = "${PN}"
PACKAGE_ARCH	 = "${MACHINE_ARCH}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   file://enigma2-plugin-systemplugins-dlnaserver_20130723.patch;striplevel=1;apply=yes \
	  "

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer
	install -m 0644 ${S}/lib/python/Plugins/Extensions/DLNAServer/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

