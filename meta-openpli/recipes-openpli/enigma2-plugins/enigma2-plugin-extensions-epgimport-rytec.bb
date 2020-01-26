DESCRIPTION = "Rytec's XMLTV sources and channels for the EPGImporter"
MAINTAINER = "Rytec forum @ forums.openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20200104"
SRC_URI = "http://rytecepg.wanwizard.eu/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

RREPLACES_${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-xmltvimport-rytec"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "124827d23c16ddac3b3aa5ee6cc391681ed29f46d77d0ae2432531608242dea8"
