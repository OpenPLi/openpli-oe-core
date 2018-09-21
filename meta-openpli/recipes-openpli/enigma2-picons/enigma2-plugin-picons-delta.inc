inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

PACKAGE_ARCH = "picons"

FILES_${PN} = "picon"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/picon
	cp  ${S}/picons/* ${D}/picon/
}


