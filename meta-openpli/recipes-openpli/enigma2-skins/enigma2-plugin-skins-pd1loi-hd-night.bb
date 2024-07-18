DESCRIPTION = "PD1LOI HD night"
MAINTAINER = "pd1loi1"
LICENSE = "AGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ae09d45eac4aa08d013b5f2e01c67f6"

inherit gitpkgv allarch python3-compileall

PV = "2.6.1+git${SRCPV}"
PKGV = "2.6.1+git${GITPKGV}"

SRC_URI = "git://github.com/PD1LOI/Pd1loi-HD-night.git;protocol=https;branch=main"

FILES:${PN} = "${prefix}"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}${prefix}
	cp -r --preserve=mode,links ${S}${prefix}/* ${D}${prefix}/
	chmod -R a+rX ${D}${prefix}
}
