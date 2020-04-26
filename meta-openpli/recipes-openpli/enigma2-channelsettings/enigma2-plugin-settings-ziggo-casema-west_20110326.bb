DESCRIPTION = "Hoeba settings Ziggo/Casema West"
MAINTAINER ?= "Mike Looijmans"

require conf/license/openpli-gplv2.inc

SRC_URI = "file://*"

PACKAGES = "${PN}"
PROVIDES="virtual/enigma2-settings"

FILES_${PN} = "${sysconfdir}/enigma2/*"
S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}/enigma2
	for f in services bouquets* userbouquet*
	do
		install -m 644 ${f} ${D}${sysconfdir}/enigma2/${f}
	done
}

inherit allarch
