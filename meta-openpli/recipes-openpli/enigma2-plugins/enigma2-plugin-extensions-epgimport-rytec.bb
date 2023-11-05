DESCRIPTION = "Rytec's XMLTV sources and channels for the EPGImporter"
MAINTAINER = "Rytec forum @ forums.openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20230617"
SRC_URI = "http://rytecepg.wanwizard.eu/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

RREPLACES_${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"

PACKAGES = "${PN}"

FILES:${PN} = "${sysconfdir}/epgimport"

do_install() {
	install -d ${D}${sysconfdir}/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}${sysconfdir}/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "63edd431e84a1b235c448a49b31edbb3c80b19422011d21139bf56ba2d4dfba3"
