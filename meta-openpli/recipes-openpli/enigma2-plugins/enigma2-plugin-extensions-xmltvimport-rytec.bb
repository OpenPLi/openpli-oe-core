DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20150411"
SRC_URI = "http://home.scarlet.be/epgalfasite/rytec.sources.xml.${PV}.gz"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "ac8b660d3e65ad709a2a8483dbebe9d4"
SRC_URI[sha256sum] = "223c939eeb189d174dc6db958a782162ebda75ec049fa88c5e320b659c01b646"

