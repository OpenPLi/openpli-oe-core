SUMMARY = "tuxbox tuxtxt for 32bit framebuffer"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "freetype libtuxtxt"
DESCRIPTION = "tuxbox tuxtxt for enigma2"

inherit gitpkgv

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/tuxtxt.git"

S = "${WORKDIR}/git/tuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r3"

PACKAGES = "${PN}-src ${PN}-dbg ${PN}-dev ${PN}"
FILES_${PN}-src = "/usr/src /usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt/*.py"
FILES_${PN} = "/usr/lib/libtuxtxt32bpp.so.* /usr/share/fonts /usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt/*.pyo /etc/tuxtxt"
CONFFILES_${PN} = "/etc/tuxtxt/tuxtxt2.conf"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-boxtype=generic --with-configdir=/etc \
	${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	DVB_API_VERSION=5\
	"

do_install_append() {
	# remove unused .pyc files
	find ${D}/usr/lib/enigma2/python/ -name '*.pyc' -exec rm {} \;
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
