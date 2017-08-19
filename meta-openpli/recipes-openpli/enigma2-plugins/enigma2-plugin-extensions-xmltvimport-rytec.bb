DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20170819"
SRC_URI = "http://www.vuplus-community.net/rytec/sources/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "8f8b4fd6434c28509e834f1d53851638e7d4e218e5801cc5e2ed999b1cca892b"

