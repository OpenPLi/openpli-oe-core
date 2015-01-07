SUMMARY = "udpxy"
MAINTAINER = "Pavel V. Cherenkov"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README;md5=bfc66ea360954327e12d62343a0ce644"

inherit gitpkgv
SRCREV = "c045a1e855a8033c5d70ab3e42271ba5636eb520"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit autotools-brokensep pkgconfig

SRC_URI = "git://github.com/pcherenkov/udpxy.git"

S = "${WORKDIR}/git/chipmunk"

FILES_${PN} = "${bindir}/*"

do_compile() {
    make -f Makefile udpxy
}

do_install() {
    install -d ${D}/${bindir}
    install -m 755 ${S}/udpxy ${D}/${bindir}
}
