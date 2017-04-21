SUMMARY = "create DVD-Video file system"
SECTION = "console/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "freetype libdvdread libfribidi libpng libxml2 zlib fontconfig"

SRC_URI = "git://github.com/ldo/dvdauthor file://fix-build.patch"

inherit gitpkgv
PV = "0.7.1+git${SRCPV}"
PKGV = "0.7.1+git${GITPKGV}"

S = "${WORKDIR}/git"

do_configure_prepend() {
	mkdir -p ${S}/autotools
	cp ${STAGING_DATADIR}/gettext/config.rpath ${S}/autotools/
}

inherit autotools gettext pkgconfig

EXTRA_OECONF = " \
        ac_cv_prog_MAGICKCONFIG= \
        ac_cv_prog_GMAGICKCONFIG= \
"

