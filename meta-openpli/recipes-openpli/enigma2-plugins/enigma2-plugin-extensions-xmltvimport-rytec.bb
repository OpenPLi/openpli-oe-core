DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20150327"
SRC_URI = "http://home.scarlet.be/epgalfasite/rytec.sources.xml.${PV}.gz"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "f1aebe2820c470bafe0443a11960dc86"
SRC_URI[sha256sum] = "e8be776795fa1802d1a23788b6542d60292f711ed5ad6b2820f207de8f2ea304"
