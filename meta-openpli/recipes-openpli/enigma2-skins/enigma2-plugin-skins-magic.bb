DESCRIPTION = "Magic skin for Enigma2"
MAINTAINER = "Mike Looijmans"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

inherit gitpkgv allarch

PV = "1.2+git${SRCPV}"
PKGV = "1.2+git${GITPKGV}"

PKGV_font-valis-enigma = "2009.11.12"
DESCRIPTION_font-valis-enigma = "Valis enigma font"

PACKAGES = "${PN} font-valis-enigma"
PROVIDES += "font-valis-enigma"

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/${BPN}.git"

FILES_${PN} = "/usr/share/enigma2/Magic"
FILES_font-valis-enigma = "/usr/share/fonts/valis_enigma.ttf"

RDEPENDS_${PN} = "font-valis-enigma"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/share
	cp -r --preserve=mode,links ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
	chmod 644 ${D}/usr/share/fonts/*.ttf
}
