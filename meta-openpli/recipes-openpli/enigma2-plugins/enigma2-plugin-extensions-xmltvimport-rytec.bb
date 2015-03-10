DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20150310"
SRC_URI = "http://home.scarlet.be/epgalfasite/rytec.sources.xml.gz"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	for f in ${S}/*.xml
	do
		install -m 644 ${f} ${D}/etc/epgimport/
	done
}

SRC_URI[md5sum] = "0052b4042965387f4cf7ec33d811c8aa"
SRC_URI[sha256sum] = "b1f2718ad3677ce86f15db1f01dec43eca37230f3f8513c9f67448d914390ebd"
