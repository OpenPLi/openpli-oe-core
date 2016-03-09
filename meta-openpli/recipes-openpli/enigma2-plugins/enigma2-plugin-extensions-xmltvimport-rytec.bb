DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20160308"
# http://epgalfasite.dyndns.tv/ resolves to:
SRC_URI = "http://rytecepg.ipservers.eu/epg_data/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "e23973ddadc095828c9fc0d0a1581ab4"
SRC_URI[sha256sum] = "bace6cd1dd9696e927e831ab46c58bbc18a9029714343caabd83c5470dea80c3"

