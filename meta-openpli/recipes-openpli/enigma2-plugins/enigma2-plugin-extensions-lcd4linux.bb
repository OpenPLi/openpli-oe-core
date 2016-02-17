SUMMARY = "LCD4Linux plugin"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?threadid=165337"
MAINTAINER = "PLi team"
LICENSE = "NPOSL-3.0"
LIC_FILES_CHKSUM = "file://usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/LICENSE;md5=a06300d1389bd32f84faeb97b6f6771f"
DEPENDS = "libusb"
PKGVERSION = "4.6-r0"
PV = "${PKGVERSION}-${SRCPV}"
PKGV = "${PKGVERSION}-${GITPKGV}"

SRC_URI = "git://github.com/eriksl/enigma2-plugin-extensions-lcd4linux-ihad-source-copy.git"
SRCREV = "fe325f8b08853e1ebee3cd4a2221f884b6236935"
S = "${WORKDIR}/git"

inherit gitpkgv pythonnative

do_compile() {
	python -m compileall ${S}/usr/lib
}

do_install() {
    cp -r --preserve=mode,links "${S}/usr" "${D}"
    cp -r --preserve=mode,links "${S}/etc" "${D}"
}

RDEPENDS_${PN} += "\
	python-codecs \
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
    png-util"

FILES_${PN} = "/usr/lib/python2.7 \
    /usr/lib/python2.7/site-packages \
    /usr/lib/enigma2/python/Components/Renderer/PixmapLcd4linux.py* \
    /usr/lib/enigma2/python/Plugins/Extensions/LCD4linux \
	/etc/enigma2/lcd4config"

CONFFILES_${PN} = "/etc/enigma2/lcd4config"

FILES_${PN}-dbg = "usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/.debug/dpflib.so"
