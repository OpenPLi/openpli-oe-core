SUMMARY = "LCD4Linux plugin"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?threadid=165337"
MAINTAINER = "PLi team"
LICENSE = "CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ca94d17fb4756a537c7aeb5cd80c3b70"
PKGVERSION = "4.2-r2"
PV = "${PKGVERSION}-${SRCPV}"
PKGV = "${PKGVERSION}-${GITPKGV}"
PR = "r0"
SRC_URI = "git://github.com/eriksl/enigma2-plugin-extensions-lcd4linux-ihad-source-copy.git"
SRCREV = "1cfe760f973fe153eb95ed54a93f4c22c8e6353c"
S = "${WORKDIR}/git"

inherit gitpkgv

do_install () {
    cp -Rp "${S}/usr" "${D}"
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

DEPENDS = "${lcd4linux_DEPENDS} libusb"
RDEPENDS_${PN} = "${lcd4linux_DEPENDS} png-util"

FILES_${PN} = "/usr/lib/python2.7 \
    /usr/lib/python2.7/site-packages \
    /usr/lib/enigma2/python/Components/Renderer/PixmapLcd4linux.py \
    /usr/lib/enigma2/python/Plugins/Extensions/LCD4linux"

FILES_${PN}-dbg = "usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/.debug/dpflib.so"
