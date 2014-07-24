SUMMARY = "LCD4Linux plugin"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?postid=2023964"
MAINTAINER = "PLi team"
LICENSE = "CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM = "file://src/LICENSE;md5=fa31f0e5b0121243d59c7ff6a908c962"
PV = "4.0+git${SRCPV}"
PKGV = "4.0+git${GITPKGV}"
PR = "r6"
SRC_URI = "git://github.com/oe-alliance/oe-alliance-plugins.git;protocol=git \
	file://01-compile-out-of-tree.patch;striplevel=1 \
"
SRCREV = "560beca22c109dc7d40962874ac945ce9e3d5e5f"
S = "${WORKDIR}/git/LCD4linux"

inherit gitpkgv autotools-brokensep

do_install_append() {
	rm -rf "${D}/usr/share"
}

lcd4linux_DEPENDS = "python-codecs \
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
	python-pyusb \
"

DEPENDS = "${lcd4linux_DEPENDS}"
RDEPENDS_${PN} = "${lcd4linux_DEPENDS} png-util"

FILES_${PN} = "/usr/lib/enigma2/python"
FILES_${PN}-dbg += "/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/.debug/dpflib.so"
