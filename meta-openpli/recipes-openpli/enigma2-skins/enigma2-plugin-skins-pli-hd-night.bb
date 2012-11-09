DESCRIPTION = "PLi HD Night skin"
MAINTAINER = "delagroov"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/share/enigma2/PLi-HD-Night/skin.xml;beginline=3;endline=8;md5=dfc1f4b5dcf9618d1764823baad5eeef"

inherit gitpkgv

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0"

PACKAGES = "${PN}"
PROVIDES = "${PN}"

SRC_URI = "git://github.com/delagroov/skin-PLi-HD-Night.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
}
