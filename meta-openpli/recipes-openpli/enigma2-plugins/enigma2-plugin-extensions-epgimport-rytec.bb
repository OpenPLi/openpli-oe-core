DESCRIPTION = "Rytec's XMLTV sources and channels for the EPGImporter"
MAINTAINER = "Rytec forum @ forums.openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20190214"
SRC_URI = "http://www.vuplus-community.net/rytec/sources/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

RREPLACES_${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "ba443d8bca64b719ebeca1fd57887937512c13449cd2177e5dc52877547b7177"
