DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20170803"
SRC_URI = "http://www.milosoftware.com/sat/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "bc782d3dcd3875a4cd7b685f4b21c40b"
SRC_URI[sha256sum] = "af2889fccb05008bc88e106f85b726a690f3e6d1583efb1bac1f7db0a4033d81"

