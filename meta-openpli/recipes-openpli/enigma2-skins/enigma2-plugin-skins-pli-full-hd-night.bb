DESCRIPTION = "PLi Full HD Night skin"
MAINTAINER = "delagroov"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/share/enigma2/PLi-Full-HD-Night/skin.xml;beginline=3;endline=8;md5=c22036c3a78d9a4f56c4ef212752b49f"

inherit gitpkgv

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0"
PACKAGE_ARCH = "all"

PACKAGES = "${PN}"
PROVIDES = "${PN}"

SRC_URI = "git://github.com/delagroov/skin-PLi-Full-HD-Night.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
}
