DESCRIPTION = "Demuxing utility"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = " \
	file://${BPN}-${PV}.tar.gz \
	file://makefile_remove_unrecognized_m32_option.patch \
	"

SRC_URI[md5sum] = "de02c1b55bc7863f811adffe611f1c71"
SRC_URI[sha256sum] = "68718d6671570815e8576b6e0b595195f5cea435194209ee5528721cb8737b63"

S = "${WORKDIR}/${BPN}-${PV}"

do_install() {
  install -d -m 0755 ${D}/${bindir}
  install -m 0755 ${S}/replex ${D}/${bindir}
}

FILES_${PN} = "${bindir}/replex"
