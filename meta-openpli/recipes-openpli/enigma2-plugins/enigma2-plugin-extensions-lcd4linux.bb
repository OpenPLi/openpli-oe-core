SUMMARY = "LCD4Linux plugin for VU+ Duo2"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?postid=2023964"
MAINTAINER = "PLi team"
LICENSE = "CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM = "file://src/LICENSE;md5=fa31f0e5b0121243d59c7ff6a908c962"
PV = "4.0+git${SRCPV}"
PKGV = "4.0+git${GITPKGV}"
PR = "r5"
SRC_URI = "git://github.com/oe-alliance/oe-alliance-plugins.git;protocol=git \
	file://01-compile-out-of-tree.patch;striplevel=1 \
"
SRCREV = "c25b354a26c397724b9db7ca10859dd86972f559"
S = "${WORKDIR}/git/LCD4linux"

inherit gitpkgv autotools

do_install_append() {
	rm -rf "${D}/usr/share"
}

DEPENDS = "python-codecs \
	python-datetime \
	python-imaging \
	python-textutils \
	python-shell \
	python-ctypes \
	python-mutagen \
	python-zlib \
	python-email \
	python-subprocess \
	python-simplejson \
"

RDEPENDS_${PN} = $DEPENDS
RDEPENDS_${PN} =+ "png-util"

FILES_${PN} = "/usr/lib/enigma2/python"
FILES_${PN}-dbg += "/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/.debug/dpflib.so"
