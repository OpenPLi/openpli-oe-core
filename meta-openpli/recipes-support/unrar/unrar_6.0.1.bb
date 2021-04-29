SUMMARY = "RAR archivers"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://license.txt;md5=fc9c335ec05a5f36764ef9ce7a79daa1"

HOMEPAGE = "http://www.rarlab.com"

SRC_URI = "http://www.rarlab.com/rar/unrarsrc-${PV}.tar.gz \
        file://makefile-nostrip.patch"

SRC_URI[md5sum] = "0f5a438ebee5cf92184d0b039e6890af"
SRC_URI[sha256sum] = "43e4d3ac762e2f58bfa9e37693efa342c1363eb1029fab409dfdf69171201450"

S = "${WORKDIR}/unrar"
TARGET_CC_ARCH += "${LDFLAGS}"

BBCLASSEXTEND = "native"
NATIVE_INSTALL_WORKS = "1"

EXTRA_OEMAKE = "-f makefile DESTDIR=${D}${exec_prefix}"

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install
}
INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

