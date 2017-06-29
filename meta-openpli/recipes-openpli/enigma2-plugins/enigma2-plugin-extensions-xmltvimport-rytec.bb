DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20170624"
SRC_URI = "http://www.milosoftware.com/sat/rytec.sources.xml.${PV}.gz"
S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "49192f9076f6be2b51b6b1dc342ca1cc"
SRC_URI[sha256sum] = "a4feecbdadae32e98967bdacb8c07b9f89084a1270c63c26d395437c5b7a90ba"

