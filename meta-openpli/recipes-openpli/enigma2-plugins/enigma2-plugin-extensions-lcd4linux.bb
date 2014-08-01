SUMMARY = "LCD4Linux plugin"
AUTHOR = "IHAD joergm6 <joergm6@www.i-have-a-dreambox.com>"
HOMEPAGE = "http://www.i-have-a-dreambox.com/wbb2/thread.php?threadid=165337"
MAINTAINER = "PLi team"
LICENSE = "NPOSL-3.0"
LIC_FILES_CHKSUM = "file://usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/LICENSE;md5=a06300d1389bd32f84faeb97b6f6771f"
PKGVERSION = "4.2-r3"
PV = "${PKGVERSION}-${SRCPV}"
PKGV = "${PKGVERSION}-${GITPKGV}"
PR = "r0"
SRC_URI = "git://github.com/eriksl/enigma2-plugin-extensions-lcd4linux-ihad-source-copy.git"
SRCREV = "5a1789c4086a1a0a8fe57b88c7d5493989a418f5"
S = "${WORKDIR}/git"

inherit gitpkgv

do_install () {
    cp -Rp "${S}/usr" "${D}"
    cp -Rp "${S}/etc" "${D}"
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
    /usr/lib/enigma2/python/Plugins/Extensions/LCD4linux \
	/etc/enigma2/lcd4config"

CONFFILES_${PN} = "/etc/enigma2/lcd4config"

FILES_${PN}-dbg = "usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/.debug/dpflib.so"
