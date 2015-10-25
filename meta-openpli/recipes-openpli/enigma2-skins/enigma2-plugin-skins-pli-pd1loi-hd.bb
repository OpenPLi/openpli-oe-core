DESCRIPTION = "Skin-PD1LOI-Full-HD"
MAINTAINER = "PD1LOI"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/share/enigma2/PD1LOI-Full-HD/skin.xml;beginline=3;endline=7;md5=6e6317a4c7d7da7fe3b079ab6f0c9a67"

inherit gitpkgv allarch

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r1"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/PD1LOI/Skin-PD1LOI-Full-HD.git"

FILES_${PN} = "/usr/share/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
}
