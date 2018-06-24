DESCRIPTION = "Frequency lists"
MAINTAINER = "PLi team"

require conf/license/openpli-gplv2.inc
inherit allarch gitpkgv

PV = "2+git${SRCPV}"
PKGV = "2+git${GITPKGV}"
PR = "3"

SRC_URI = "git://github.com/OpenPLi/tuxbox-xml.git;protocol=git"
S = "${WORKDIR}/git/xml"
FILES_${PN} = "${sysconfdir}/tuxbox/* ${prefix}/* ${localstatedir}/*"

do_compile() {
	true
}

do_install() {
	install -m 0755 -d "${D}${sysconfdir}"
	install -m 0755 -d "${D}${sysconfdir}/tuxbox"
	install -m 0755 -d "${D}${sysconfdir}/tuxbox/scce"
	install -m 0755 -d "${D}${prefix}/keys"
	install -m 0755 -d "${D}${bindir}"
	install -m 0755 -d "${D}${localstatedir}"

	install -m 0644 "${S}"/*.xml "${D}${sysconfdir}/tuxbox"

	ln -s "${sysconfdir}/tuxbox/scce"	"${D}${localstatedir}/"
	ln -s "${prefix}/keys"			"${D}${localstatedir}/"
	ln -s "${bindir}"			"${D}${localstatedir}/"
	ln -s "${sysconfdir}"			"${D}${localstatedir}/"
}
