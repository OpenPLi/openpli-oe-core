DESCRIPTION = "mounts a DVD using libdvdread"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

HOMEPAGE = "http://www.jspenguin.org/software/dvdfs/"
DEPENDS = "fuse libdvdread"

inherit pkgconfig

SRC_URI = "https://www.ktpanda.org/software/${BPN}/${BPN}-${PV}.tar.gz \
	file://crosscompile.patch \
	file://defaultdevicesr0.patch \
	"
TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/${PN} ${D}${bindir}/${PN}
}

SRC_URI[md5sum] = "e9332b7eaf7dd875cea1700d4f7fa52e"
SRC_URI[sha256sum] = "9e9f9b141aeb98a1f75985e342aa39a89e247e8ec594309fb2d80d9ab0b654a3"
