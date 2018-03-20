SUMMARY= "Daemon for network time"
SECTION = "network"
LICENSE = "GPLv2"

require conf/license/license-gplv2.inc

RDEPENDS_${PN} = "busybox"

SRC_URI = " \
	file://ntpd-helper.sh \
	file://ntpd-helper.conf \
"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "ntpd-helper"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 ."

do_configure () {
}

do_compile () {
}

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ntpd-helper.sh ${D}/${sysconfdir}/init.d/ntpd-helper
	install -m 0755 ${WORKDIR}/ntpd-helper.conf ${D}/${sysconfdir}/ntpd-helper.conf
}

CONFFILES = "/etc/ntpd-helper.conf"
FILES_${PN} = "/etc/init.d /etc"