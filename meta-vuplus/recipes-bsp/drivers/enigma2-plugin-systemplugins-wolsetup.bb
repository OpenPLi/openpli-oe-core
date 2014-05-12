inherit gitpkgv

DESCRIPTION	 = "VU+ WOLSetup plugin"
LICENSE		 = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV		 = "8d6204fa8ce5d67ebc0d5dbae5c77cce481b503d"
PV		 = "experimental-git${SRCPV}"
PKGV		 = "experimental-git${GITPKGV}"
PR		 = "r0"
BRANCH		 = "vuplus_experimental"
DEPENDS		 = "vuplus-coldboot"
PROVIDES	 = "enigma2-plugin-systemplugins-wolsetup"
RDEPENDS_${PN}	 = "vuplus-coldboot"
FILES_${PN}	 = "/usr/lib/enigma2/python/Plugins/SystemPlugins/WOLSetup/*"
PACKAGES	 = "${PN}"
PACKAGE_ARCH	 = "${MACHINE_ARCH}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   file://wolsetting-deepstandby_20130718.patch \
	  "

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WOLSetup
	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/WOLSetup/*.py ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WOLSetup
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}


