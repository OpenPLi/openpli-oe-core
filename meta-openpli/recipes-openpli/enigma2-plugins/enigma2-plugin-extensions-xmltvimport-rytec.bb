DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20150629"
SRC_URI = "http://epgalfasite.dyndns.tv/rytec.sources.29062015.zip"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "9b45de6ad7caf22edfe3180f4b7dfc39"
SRC_URI[sha256sum] = "fec5d748d3ddaf51044b0aee0972e1bff0b3e92ac90f8c61bab054fdda544112"


