SUMMARY = "A set of tools for CD recording"
HOMEPAGE = "http://www.cdrkit.org"
DEPENDS = "libcap bzip2 zlib"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b30d3b2750b668133fc17b401e1b98f8"

SRC_URI = " \
	http://pkgs.fedoraproject.org/repo/pkgs/cdrkit/${P}.tar.gz/efe08e2f3ca478486037b053acd512e9/${P}.tar.gz \
	file://xconfig.patch \
"

inherit cmake

SRC_URI[md5sum] = "efe08e2f3ca478486037b053acd512e9"
SRC_URI[sha256sum] = "d1c030756ecc182defee9fe885638c1785d35a2c2a297b4604c0e0dcc78e47da"
