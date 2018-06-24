SUMMARY = "Hard disk temperature monitor daemon"
SECTION = "console/network"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://GPL-2;md5=eb723b61539feef013de476e68b5c50a"
PR = "r1"

SRC_URI = "https://download.savannah.nongnu.org/releases/${PN}/${PN}-${PV}.tar.bz2;name=tar \
		file://hddtemp.db \
		file://hddtemp-no-nls-support.patch"
SRC_URI[tar.md5sum] = "8b829339e1ae9df701684ec239021bb8"
SRC_URI[tar.sha256sum] = "618541584054093d53be8a2d9e81c97174f30f00af91cb8700a97e442d79ef5b"

inherit autotools gettext

FILES_${PN} += "${datadir}/misc/hddtemp.db"

do_install_append() {
	install -d ${D}${datadir}/misc/
	install -m 0644 ${WORKDIR}/hddtemp.db ${D}${datadir}/misc/hddtemp.db
}
