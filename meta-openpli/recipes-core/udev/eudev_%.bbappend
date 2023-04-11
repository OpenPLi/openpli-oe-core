FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://init \
	file://60-ssd-scheduler.rules \
	"

do_install_append() {
	install -m 0644 ${WORKDIR}/60-ssd-scheduler.rules ${D}${sysconfdir}/udev/rules.d/60-ssd-scheduler.rules
}

DEPENDS += " udev-extraconf"
RDEPENDS_${PN} += " udev-extraconf"
