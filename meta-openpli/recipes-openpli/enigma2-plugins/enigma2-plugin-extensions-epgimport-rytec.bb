DESCRIPTION = "Rytec's XMLTV sources and channels for the EPGImporter"
MAINTAINER = "Rytec forum @ forums.openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20241009"
SRC_URI = "http://rytecepg.wanwizard.eu/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

RREPLACES:${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"
RCONFLICTS:${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"

PACKAGES = "${PN}"

FILES:${PN} = "${sysconfdir}/epgimport"

do_install() {
	install -d ${D}${sysconfdir}/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}${sysconfdir}/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "dba0bfa44f54f4fe87653f7417924b2bd9fa98690d83da9e58111ec03eb62acf"
