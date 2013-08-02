inherit gitpkgv

SRC_DATE	 = "20130723_p0"
DESCRIPTION	 = "VU+ Stream TV Player plugin"
LICENSE		 = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV		 = "8d6204fa8ce5d67ebc0d5dbae5c77cce481b503d"
PV		 = "experimental-git${SRCPV}"
PKGV		 = "experimental-git${GITPKGV}"
PR		 = "${SRC_DATE}_r0"
BRANCH		 = "vuplus_experimental"
DEPENDS		 = "librtmp gst-plugins-bad"
PROVIDES	 = "enigma2-plugin-extensions-streamtv"
RDEPENDS_${PN}	 = "librtmp gst-plugins-bad-rtmp"
FILES_${PN}	 = "/usr/lib/enigma2/python/Plugins/Extensions/StreamTV/*"
PACKAGES	 = "${PN}"
PACKAGE_ARCH	 = "${MACHINE_ARCH}"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	   http://archive.vuplus.com/openpli-support/vuplus-streamtv-imagepack_${SRC_DATE}.tar.gz;name=imagepack \
	   file://enigma2-plugin-systemplugins-streamtv_20130723.patch;striplevel=1;apply=yes \
	  "

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV/icons
	install -m 0644 ${WORKDIR}/vuplus-streamtv-imagepack/*.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV
	install -m 0644 ${WORKDIR}/vuplus-streamtv-imagepack/*.xml ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV
	install -m 0644 ${WORKDIR}/vuplus-streamtv-imagepack/icons/*.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV/icons/

	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV
	install -m 0644 ${S}/lib/python/Plugins/Extensions/StreamTV/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

SRC_URI[imagepack.md5sum] = "bd80b4322171462f0c215f350d9841dc"
SRC_URI[imagepack.sha256sum] = "d81842f9c230f071be49909d5e3dd97a1b4f15a0335f884737e14a3e31bd543e"

