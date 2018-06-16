DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ openpli.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20180613"
SRC_URI = "http://www.vuplus-community.net/rytec/sources/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "497592e5859b3188dbc05706963681d59cce270b9a17214c66cf15b0ec9a2922"