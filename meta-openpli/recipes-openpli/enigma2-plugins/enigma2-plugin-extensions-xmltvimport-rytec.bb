DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20151030"
# http://epgalfasite.dyndns.tv/ resolves to:
SRC_URI = "http://rytecepg.ipservers.eu/epg_data/rytec.sources.xml.30102015.zip"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "15c9a4530c64dc5c96075bea95f176b0"
SRC_URI[sha256sum] = "e092a01407c00fd64edba5437bc588c96786b68eab915c17d60d8c02eddc8c51"
