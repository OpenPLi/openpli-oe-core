DESCRIPTION = "Frequency lists"
MAINTAINER = "PLi team"

require conf/license/openpli-gplv2.inc
inherit allarch gitpkgv

PV = "2+git${SRCPV}"
PKGV = "2+git${GITPKGV}"

SRC_URI = "git://github.com/OpenPLi/tuxbox-xml.git;protocol=git"
S = "${WORKDIR}/git/xml"
FILES_${PN} = "${sysconfdir}/tuxbox/*"

do_compile() {
	true
}

do_install() {
	install -m 0755 -d "${D}${sysconfdir}/tuxbox"
	install -m 0644 "${S}"/*.xml "${D}/${sysconfdir}/tuxbox/$i"
}
