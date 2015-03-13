DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20150312"
SRC_URI = "http://home.scarlet.be/epgalfasite/rytec.sources.xml.${PV}.gz"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "6dca3b8604cff44818775d975cc37119"
SRC_URI[sha256sum] = "57089e9dd0f3964ed381dad3f59dd7350016e98b4167a572003bd71095682277"
