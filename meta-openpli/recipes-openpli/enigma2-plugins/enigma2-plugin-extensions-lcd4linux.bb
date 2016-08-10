SUMMARY = "LCD4Linux plugin"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?threadid=165337"
MAINTAINER = "PLi team"
LICENSE = "NPOSL-3.0"
LIC_FILES_CHKSUM = "file://usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/LICENSE;md5=a06300d1389bd32f84faeb97b6f6771f"
DEPENDS = "libusb"
PKGVERSION = "4.7-r3"
PV = "${PKGVERSION}-${SRCPV}"
PKGV = "${PKGVERSION}-${GITPKGV}"

SRC_URI = "git://github.com/eriksl/enigma2-plugin-extensions-lcd4linux-ihad-source-copy.git"
SRCREV = "c4b24cf002ff8342c72b86a362d5fe773578cbe4"
S = "${WORKDIR}/git"

inherit gitpkgv pythonnative allarch

do_compile() {
	python -m compileall ${S}/usr/lib
}

do_install() {
	cp -Rp "${S}/usr" "${D}"
	cp -Rp "${S}/etc" "${D}"
	rm "${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/dpflib.so"
}

FILES_${PN} = "\
	/usr/lib/python2.7 \
	/usr/lib/python2.7/site-packages \
	/usr/lib/enigma2/python/Components/Renderer/PixmapLcd4linux.py* \
	/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux \
	/etc/enigma2/lcd4config"

CONFFILES_${PN} = "/etc/enigma2/lcd4config"
