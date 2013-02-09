DESCRIPTION = "PNG Library"
HOMEPAGE = "http://www.libpng.org/"
LICENSE = "libpng"
SECTION = "libs"
PRIORITY = "required"
LICENSE = "Libpng"
LIC_FILES_CHKSUM = "file://LICENSE;md5=58c8238139ee86082f8d29eb89304241"

DEPENDS = "zlib"

PR = "r0"

SRC_URI = "ftp://ftp.simplesystems.org/pub/png/src/libpng-${PV}.tar.bz2;name=libpng"
S = "${WORKDIR}/libpng-${PV}"

inherit autotools pkgconfig binconfig

SRC_URI[libpng.md5sum] = "d7a89c2dc12e439f0c84306a06ceafdd"
SRC_URI[libpng.sha256sum] = "bd6bab0534d75ceec08be3ddc08d57096013b829f81cd39ebd468f2c7d10d1bc"

PACKAGES =+ "${PN}15"

FILES_${PN}15 = "${libdir}/libpng15${SOLIBS}"
FILES_${PN} = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-dev += " ${bindir} ${sbindir}"
RPROVIDES_${PN}-dev += "${PN}15-dev"

BBCLASSEXTEND = "native"
