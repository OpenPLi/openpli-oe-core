DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20170807"
SRC_URI = "http://www.milosoftware.com/sat/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[sha256sum] = "1b9f17a6845357b9c8cd4be495581aa6825ef2bd55da9f3b6880398d01c22b9a"

