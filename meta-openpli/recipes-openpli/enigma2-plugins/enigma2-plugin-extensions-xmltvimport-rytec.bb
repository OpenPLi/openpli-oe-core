DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20151011"
# http://epgalfasite.dyndns.tv/ resolves to:
SRC_URI = "http://rytecepg.ipservers.eu/epg_data/rytec.sources.xml.${PV}.gz"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "35ffd72e6ea9a59a390523d202fb5e62"
SRC_URI[sha256sum] = "a3d1d40c2d0978b95c4a06e589bee2c9ee86681c4c8d94f4cef6ad92c3ba8fde"
