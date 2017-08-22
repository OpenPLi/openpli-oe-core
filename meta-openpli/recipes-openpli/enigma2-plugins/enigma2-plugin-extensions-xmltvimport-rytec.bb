DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20170822"
SRC_URI = "http://www.vuplus-community.net/rytec/sources/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "c488a047947b32b5c0de194ac05fdaa2053c0444e9424792ae089e683fa6a434"

