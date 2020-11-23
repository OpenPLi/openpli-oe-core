DESCRIPTION = "mount UPnP server content as a linux filesystem"
HOMEPAGE = "http://djmount.sourceforge.net/"
LICENSE = "GPLv2+"
DEPENDS = "libupnp fuse"
RDEPENDS_${PN} = "libupnp fuse"

LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

INITSCRIPT_NAME = "djmount"
INITSCRIPT_PARAMS = "defaults"

CFLAGS_append+= "-std=gnu89"

SRC_URI = "git://github.com/mbarbon/djmount.git;protocol=http;branch=fixes"

SRCREV = "02d7d47c4f04054a8a1c174b75839ee38682af86"
PR="2"

SRC_URI_append +=" \
	file://init \
	file://04-support-fstab-mounting.patch \
	file://missing_header.patch \
	"
EXTRA_OECONF = "--with-external-libupnp-prefix='${STAGING_LIBDIR}' --with-fuse-prefix='${STAGING_LIBDIR}'"

do_configure_prepend() {
	mkdir ${S}/libupnp/config.aux/
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/libupnp/config.aux/config.rpath
}

S = "${WORKDIR}/git"

inherit autotools update-rc.d pkgconfig gettext

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/djmount
}
