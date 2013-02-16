inherit gitpkgv

PN = "enigma2-plugin-skins-pli-scale-hd"

SUMMARY = "PLi-Scale-HD skin, scalable clean maximum screen utilisation"
DESCRIPTION = "Screen based on resizable templates."
MAINTAINER = "Stephen R. van den Berg AKA BuGless <srb@cuci.nl>"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENSE;md5=8c6df343eefac4184a40b488b231ab9f"

SRCREV = ""
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"
PACKAGE_ARCH = "all"

PACKAGES = "${PN}"
PROVIDES = "${PN}"
BRANCH = "master"

SRC_URI = "git://devel.cuci.nl/enigma2-skin-pli-scale-hd;protocol=git;branch=${BRANCH}"

FILES_${PN} = "/"
RDEPENDS_${PN} = "enigma2-fonts"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	${S}/install.sh "${S}" "${D}"
}
