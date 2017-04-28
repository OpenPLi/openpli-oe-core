DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20160913"
SRC_URI = "http://www.milosoftware.com/sat/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "f3be792cd1cf1735fd97978dd16a4c15"
SRC_URI[sha256sum] = "06037068aba80e0a63e7adffd6478e3f2b98dc4bb369e31435d4d8bfed5d4348"

