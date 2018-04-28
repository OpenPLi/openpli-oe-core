DESCRIPTION = "PD1LOI HD night"
MAINTAINER = "pd1loi1"
LICENSE = "GPLv3"

inherit gitpkgv allarch

PV = "2.6.1+git${SRCPV}"
PKGV = "2.6.1+git${GITPKGV}"

SRC_URI = "https://github.com/PD1LOI/Pd1loi-HD-night-skin"

FILES_${PN} = "/usr"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr
	cp -r --preserve=mode,links ${S}/usr/* ${D}/usr/
	chmod -R a+rX ${D}/usr
}
