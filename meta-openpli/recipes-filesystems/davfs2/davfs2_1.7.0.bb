DESCRIPTION = "A Linux file system driver that allows you to mount a WebDAV server as a disk drive."
SECTION = "network"
PRIORITY = "optional"
HOMEPAGE = "https://git.savannah.nongnu.org/cgit/davfs2.git/"
DEPENDS = "gettext-native neon"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

SRC_URI[sha256sum] = "251db75a27380cca1330b1b971700c5e5dcc0c90e5a47622285f0140edfe3a2f"

SRC_URI = "http://download.savannah.nongnu.org/releases/davfs2/${P}.tar.gz \
           file://neon-config \
           file://volatiles \
           file://davfs2.cfg \
"
DEPENDS = "fuse libdvdread neon"

inherit autotools pkgconfig useradd gettext

USERADD_PACKAGES = "davfs2"
USERADD_PARAM:davfs2 = "--system --home /var/run/mount.davfs \
                        --no-create-home --shell /bin/false \
                        --user-group davfs2"

EXTRA_OECONF = "--with-neon \
                ac_cv_path_NEON_CONFIG=${WORKDIR}/neon-config"


CONFFILES:${PN} = "${sysconfdir}/davfs2/davfs2.conf ${sysconfdir}/davfs2/secrets"

do_install:prepend () {
	cp ${WORKDIR}/davfs2-${PV}/etc/davfs2.conf ${WORKDIR}/build/etc
	cp ${WORKDIR}/davfs2-${PV}/etc/secrets ${WORKDIR}/build/etc
}

do_install:append () {
        mkdir -p ${D}${sysconfdir}/default/volatiles
        install -m 644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/10_davfs2
        rm -rf ${D}${datadir}/davfs2
}
