DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PR = "r0"

SRC_URI = "http://www.rytec.be/tools/rytec.sources.xml.${PV}.zip"

S = "${WORKDIR}"

DEPENDS = "enigma2-plugin-extensions-xmltvimport"
PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	for f in ${S}/*.xml
	do
		install -m 644 ${f} ${D}/etc/epgimport/
	done
}

SRC_URI[md5sum] = "43143f3d3d3564dad6cf2931ca657663"
SRC_URI[sha256sum] = "0593350a3faa163fda4d5ef1c54d9f8534a9ad6aa98eba6271525015948b2ee2"
