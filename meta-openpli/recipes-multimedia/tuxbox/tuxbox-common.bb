DESCRIPTION = "Frequency lists"
MAINTAINER = "PLi team"

require conf/license/openpli-gplv2.inc
inherit allarch gitpkgv

PV = "2+git${SRCPV}"
PKGV = "2+git${GITPKGV}"
PR = "2"

SRC_URI = "git://github.com/OpenPLi/tuxbox-xml.git;protocol=git"
S = "${WORKDIR}/git/xml"
FILES_${PN} = "${sysconfdir}/tuxbox/*"

do_compile() {
	true
}

do_install() {
	install -m 0755 -d "${D}${sysconfdir}/tuxbox"
	install -m 0644 "${S}"/*.xml "${D}/${sysconfdir}/tuxbox/$i"

	install -m 0755 -d ${D}/usr/keys
	install -m 0755 -d ${D}/usr/bin
	install -m 0755 -d ${D}/etc/tuxbox/scce
	install -m 0755 -d ${D}/var

	ln -s /usr/keys ${D}/var/
	ln -s /usr/bin ${D}/var/
	ln -s /etc/tuxbox/scce ${D}/var/
	ln -s /etc ${D}/var/
}
