DESCIPTION = "check vfd firmware"
MAINTAINER = "vuplus team"
LICENSE = "CLOSED"

SRCDATE="20130723_p0"

PV = "1.0"
PR = "${SRCDATE}_r0"
SRC_REV = ""

SRC_URI = "http://archive.vuplus.com/openpli-support/vuplus-checkvfd_${SRCDATE}.tar.gz"

do_install() {
	install -d ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/FirmwareUpgrade
	install -m 0755 ${WORKDIR}/${PN}/checkvfd ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/FirmwareUpgrade
}

PACKAGES = "${PN}"
FILES_${PN} = "/"

SRC_URI[md5sum] = "c5d0a41ba77e49ad7f5a9bd2afaecc88"
SRC_URI[sha256sum] = "7add25e46d42066a913b7d7f9c25462ea01d6e957f689a84c93c59a50871d7ab"
