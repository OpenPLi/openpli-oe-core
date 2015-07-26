DESCRIPTION = "Tuxbox common files"
LICENSE = "CLOSED"
MAINTAINER = "PLi team"

inherit allarch gitpkgv

SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
SRC_URI = "git://github.com/OpenPLi/OpenPLi-1.git"

S = "${WORKDIR}/git/cdk/cdk/root"

FILES_${PN} = "${sysconfdir}"

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml"

do_compile() {
	true
}

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/
	install -m 0644 ${S}/share/tuxbox/scart.conf ${D}${sysconfdir}/tuxbox/scart.conf
	install -m 0644 ${S}/etc/timezone.xml ${D}${sysconfdir}/tuxbox/timezone.xml

	ln -sf tuxbox/timezone.xml ${D}${sysconfdir}/timezone.xml

	for i in ${TRANSPONDER_LISTS}; do
		install -m 0644 ${S}/share/tuxbox/$i ${D}${sysconfdir}/tuxbox/$i
	done
}
