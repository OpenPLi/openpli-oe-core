FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://init \
	file://60-ssd-scheduler.rules \
	"

do_install:append() {
	install -m 0644 ${WORKDIR}/60-ssd-scheduler.rules ${D}${sysconfdir}/udev/rules.d/60-ssd-scheduler.rules
}

DEPENDS += " udev-extraconf"
RDEPENDS:${PN} += " udev-extraconf"
