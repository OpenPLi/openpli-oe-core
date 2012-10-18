DESCRIPTION = "PNG Library"
HOMEPAGE = "http://www.libpng.org/"
LICENSE = "libpng"
SECTION = "libs"
PRIORITY = "required"
LICENSE = "Libpng"
LIC_FILES_CHKSUM = "file://LICENSE;md5=00b5b35853278d508806c2e5860e82cb"

DEPENDS = "zlib"

PR = "r0"

SRC_URI = "ftp://ftp.simplesystems.org/pub/png/src/libpng-${PV}.tar.bz2;name=libpng"
S = "${WORKDIR}/libpng-${PV}"

inherit autotools pkgconfig binconfig

SRC_URI[libpng.md5sum] = "186b3098d1ef844f76681bc69968efe2"
SRC_URI[libpng.sha256sum] = "16e761e584e5d72d72fc80923e7851a468bf86538456e304aea401b99038ca92"

PACKAGES =+ "${PN}15"

FILES_${PN}15 = "${libdir}/libpng15${SOLIBS}"
FILES_${PN} = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-dev += " ${bindir} ${sbindir}"
RPROVIDES_${PN}-dev += "${PN}15-dev"

BBCLASSEXTEND = "native"
