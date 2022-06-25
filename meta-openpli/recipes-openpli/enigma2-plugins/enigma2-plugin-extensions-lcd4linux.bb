SUMMARY = "LCD4Linux plugin"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?threadid=165337"
MAINTAINER = "PLi team"
LICENSE = "NPOSL-3.0"
LIC_FILES_CHKSUM = "file://usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/LICENSE;md5=a06300d1389bd32f84faeb97b6f6771f"

PKGVERSION = "5.0-r4"
PV = "${PKGVERSION}-${SRCPV}"
PKGV = "${PKGVERSION}-${GITPKGV}"

SRC_URI = "git://github.com/Hains/enigma2-plugin-extensions-lcd4linux-ihad-source-copy.git;protocol=https"

RDEPENDS_${PN} += "\
	png-util \
	pydpflib \
	${PYTHON_PN}-codecs \
	${PYTHON_PN}-ctypes \
	${PYTHON_PN}-datetime \
	${PYTHON_PN}-email \
	${PYTHON_PN}-image \
	${PYTHON_PN}-mutagen \
	${PYTHON_PN}-pyusb \
	${PYTHON_PN}-shell \
	${PYTHON_PN}-simplejson \
	${PYTHON_PN}-pillow \
"

S = "${WORKDIR}/git"

inherit gitpkgv python3native

do_compile() {
	python3 -m compileall ${S}${libdir}
}

do_install() {
	cp -r --preserve=mode,links "${S}${prefix}" "${D}"
	cp -r --preserve=mode,links "${S}${sysconfdir}" "${D}"
}

FILES_${PN} = "\
	${libdir}/${PYTHON_DIR} \
	${libdir}/${PYTHON_DIR}/site-packages \
	${libdir}/enigma2/python/Components/Renderer/PixmapLcd4linux.py* \
	${libdir}/enigma2/python/Plugins/Extensions/LCD4linux \
	${sysconfdir}/enigma2/lcd4config*"

CONFFILES_${PN} = "${sysconfdir}/enigma2/lcd4config"
