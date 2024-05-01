DESCRIPTION = "PD1LOI HD night"
MAINTAINER = "pd1loi1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README.md;md5=bf787cb63f6ed5c8a1faf7273fe86695"

inherit gitpkgv allarch python3-compileall

PV = "2.6.1+git${SRCPV}"
PKGV = "2.6.1+git${GITPKGV}"

SRC_URI = "git://github.com/OpenPLi/Pd1loi-HD-night.git;protocol=https;branch=main"

FILES_${PN} = "${prefix}"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}${prefix}
	cp -r --preserve=mode,links ${S}${prefix}/* ${D}${prefix}/
	chmod -R a+rX ${D}${prefix}
}
