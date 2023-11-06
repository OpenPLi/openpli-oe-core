SUMMARY = "create DVD-Video file system"
SECTION = "console/multimedia"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "freetype libdvdread fribidi libpng libxml2 zlib fontconfig gettext bison-native"

SRC_URI = "git://github.com/ldo/dvdauthor.git;protocol=https;branch=master \
	file://fix-build.patch"

inherit gittag autotools gettext pkgconfig

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

S = "${WORKDIR}/git"

do_configure:prepend() {
	mkdir -p ${S}/autotools
	cp ${STAGING_DATADIR}/gettext/config.rpath ${S}/autotools/
}

EXTRA_OECONF = " \
        ac_cv_prog_MAGICKCONFIG= \
        ac_cv_prog_GMAGICKCONFIG= \
"

