DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20170817"
SRC_URI = "http://www.vuplus-community.net/sources/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "45A05F952B95D5A5DA525341E4326CC165C07C617164B394D9E877860E1B504C"

