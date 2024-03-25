DESCRIPTION = "PD1LOI HD night"
MAINTAINER = "pd1loi1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README.md;beginline=6;endline=6;md5=68b329da9893e34099c7d8ad5cb9c940"

inherit gitpkgv allarch python3-compileall

PV = "2.6.1+git${SRCPV}"
PKGV = "2.6.1+git${GITPKGV}"

SRC_URI = "git://github.com/PD1LOI/Pd1loi-HD-night.git;protocol=https"

FILES_${PN} = "${prefix}"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}${prefix}
	cp -r --preserve=mode,links ${S}${prefix}/* ${D}${prefix}/
	chmod -R a+rX ${D}${prefix}
}
