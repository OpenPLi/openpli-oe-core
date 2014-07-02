DESCRIPTION = "PLi HD FullNight skin"
MAINTAINER = "littlesat"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/share/enigma2/PLi-HD-FullNight/skin.xml;beginline=3;endline=8;md5=2c71d0de3954aab943a5eccb0fe4dd04"

inherit gitpkgv

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0"
PACKAGE_ARCH = "all"

SRC_URI = "git://github.com/littlesat/PLi-HD-FullNight.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
}
