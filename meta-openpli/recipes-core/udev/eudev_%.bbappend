FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://init \
	"

DEPENDS += " udev-extraconf"
RDEPENDS_${PN} += " udev-extraconf"
