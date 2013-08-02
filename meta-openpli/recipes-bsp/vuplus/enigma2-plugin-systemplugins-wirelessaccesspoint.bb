inherit gitpkgv

SRC_DATE	 = "20130723_p0"
DESCRIPTION	 = "VU+ Wireless AP plugin"
LICENSE		 = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV		 = "8d6204fa8ce5d67ebc0d5dbae5c77cce481b503d"
PV		 = "experimental-git${SRCPV}"
PKGV		 = "experimental-git${GITPKGV}"
PR		 = "${SRC_DATE}_r0"
BRANCH		 = "vuplus_experimental"
DEPENDS		 = "hostap-daemon bridge-utils rt3070 firmware-rt3070"
PROVIDES	 = "enigma2-plugin-systemplugins-wirelessaccesspoint"
RDEPENDS_${PN}	 = "hostap-daemon bridge-utils rt3070 firmware-rt3070"
FILES_${PN}	 = "/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint/*"
PACKAGES	 = "${PN}"
PACKAGE_ARCH	 = "${MACHINE_ARCH}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   http://archive.vuplus.com/openpli-support/vuplus-wirelessap_${SRC_DATE}.tar.gz;name=libwap \
	  "

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint 
	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/WirelessAccessPoint/*.py ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint
	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/WirelessAccessPoint/*.orig ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint
	install -m 0755 ${WORKDIR}/vuplus-wirelessap/*.so ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

SRC_URI[libwap.md5sum] = "817856c9eefada7178223c6b21e914b1"
SRC_URI[libwap.sha256sum] = "87be0d0fe5bfd9f4639cf9c12cbf466f93011504c7c7143aa9f1e7c807c38f8b"
