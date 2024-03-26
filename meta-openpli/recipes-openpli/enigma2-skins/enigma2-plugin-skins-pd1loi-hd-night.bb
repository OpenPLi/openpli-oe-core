DESCRIPTION = "PD1LOI HD night"
MAINTAINER = "pd1loi1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README.md;md5=4e92522262895e6aa6977828a170c78b"

inherit gitpkgv allarch

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
