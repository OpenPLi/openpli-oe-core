DESCRIPTION = "mount UPnP server content as a linux filesystem"
HOMEPAGE = "http://djmount.sourceforge.net/"
LICENSE = "GPL-2.0-or-later"
DEPENDS = "libupnp fuse"
RDEPENDS:${PN} = "libupnp fuse"

LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

INITSCRIPT_NAME = "djmount"
INITSCRIPT_PARAMS = "defaults"

inherit autotools update-rc.d pkgconfig gettext

# libupnp make doesn't support it
PARALLEL_MAKE = ""

SRC_URI = "git://github.com/SHTrassEr/djmount.git;protocol=https;branch=master"

CFLAGS:append = " -std=gnu89 -fcommon"

SRC_URI:append =" \
	file://init \
	file://01-configure.ac.patch \
	file://02-rt_bool_arg_enable.m4.patch \
	file://03-djmount.1.patch \
	file://04-support-fstab-mounting.patch \
	file://05-fix-build-with-gettext-0.20.x.patch \
	"
EXTRA_OECONF = "--with-external-libupnp-prefix='${STAGING_LIBDIR}' --with-fuse-prefix='${STAGING_LIBDIR}'"

S = "${WORKDIR}/git"

do_configure:prepend() {
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/libupnp/config.aux/config.rpath
}
do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/djmount
}
