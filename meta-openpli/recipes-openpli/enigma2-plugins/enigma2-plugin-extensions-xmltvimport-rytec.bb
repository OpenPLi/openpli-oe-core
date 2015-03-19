DESCRIPTION = "Rytec's sources and channels for the XMLTV importer"
MAINTAINER = "MiLo, rytec @ pli-images.org"
PACKAGE_ARCH = "all"

require conf/license/openpli-gplv2.inc

inherit allarch

PV = "20150318"
SRC_URI = "http://home.scarlet.be/epgalfasite/rytec.sources.xml.${PV}.gz"

S = "${WORKDIR}"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/epgimport"

do_install() {
	install -d ${D}/etc/epgimport
	install -m 644 ${S}/rytec.sources.xml.${PV} ${D}/etc/epgimport/rytec.sources.xml
}

SRC_URI[md5sum] = "742f5154cf254534fb24bc3d1b05139f"
SRC_URI[sha256sum] = "d922b0599a309f18c420e48ae1806e98c7faf730ba8f064b04ad536fa6ec9987"
