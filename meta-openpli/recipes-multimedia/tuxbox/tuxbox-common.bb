DESCRIPTION = "Tuxbox common files"
LICENSE = "CLOSED"
MAINTAINER = "PLi team"

inherit allarch gitpkgv

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
SRC_URI = "git://github.com/OpenPLi/OpenPLi-1.git"

S = "${WORKDIR}/git/cdk/cdk/root"

FILES_${PN} = "${sysconfdir}"

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml atsc.xml"

do_compile() {
	true
}

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/
	install -m 0644 ${S}/share/tuxbox/scart.conf ${D}${sysconfdir}/tuxbox/scart.conf

	for i in ${TRANSPONDER_LISTS}; do
		install -m 0644 ${S}/share/tuxbox/$i ${D}${sysconfdir}/tuxbox/$i
	done
}
