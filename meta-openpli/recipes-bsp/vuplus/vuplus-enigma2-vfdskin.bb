require conf/license/openpli-gplv2.inc

RDEPENDS_${PN} = "enigma2"

COMPATIBLE_MACHINE = "vuduo2|vuultimo"

SRCDATE = "20130801_p0"
PR = "${SRCDATE}_r0"

SRC_URI = " \
	http://archive.vuplus.com/openpli-support/vfd_icons_vuduo2_${SRCDATE}.tar.gz;name=${MACHINE} \
	file://skin_user_${MACHINE}.xml \
	"

do_install() {
	install -d ${D}/usr/share/enigma2/
	install -m 0644 ${WORKDIR}/skin_user_${MACHINE}.xml ${D}/usr/share/enigma2/skin_display.xml
        install -d ${D}/usr/share/enigma2/vfd_icons/
        install -m 0644 ${WORKDIR}/vfd_icons_${MACHINE}/*.png ${D}/usr/share/enigma2/vfd_icons/
}

FILES_${PN} = "/usr/share/enigma2/skin_display.xml /usr/share/enigma2/vfd_icons/*"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[vuduo2.md5sum] = "156b3a746c99ce546bb1421a9b1ef3ad"
SRC_URI[vuduo2.sha256sum] = "546d974ff4f98d00f18666b9329c5365510231592369899a0cd630e1d09b71fa"
SRC_URI[vuultimo.md5sum] = "588152160e25a2f8b630c03ae3d4aad0"
SRC_URI[vuultimo.sha256sum] = "f25f014ea7c367143608baa396ca1fd0e0fbac3011a7df37e3687f7e70b5fb5d"

