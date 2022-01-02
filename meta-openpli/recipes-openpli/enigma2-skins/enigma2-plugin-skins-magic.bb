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

SRC_URI = "git://github.com/OpenPLi/${BPN}.git;protocol=https"

FILES_${PN} = "${datadir}/enigma2/Magic"
FILES_font-valis-enigma = "${datadir}/fonts/valis_enigma.ttf"

RDEPENDS_${PN} = "font-valis-enigma"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${datadir}
	cp -r --preserve=mode,links ${S}${datadir}/* ${D}${datadir}/
	chmod -R a+rX ${D}${datadir}/enigma2/
	chmod 644 ${D}${datadir}/fonts/*.ttf
}
