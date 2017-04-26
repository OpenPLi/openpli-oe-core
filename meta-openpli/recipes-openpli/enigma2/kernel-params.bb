PV = "20110114"
DESCRIPTION = "Tune kernel parameters in /etc/sysconf"

require conf/license/openpli-gplv2.inc

PACKAGES = "${PN}"

SRC_URI = "file://sysctl.conf"

# Users may chose to edit or create their own
CONFFILES_${PN} = "${sysconfdir}/sysctl.conf"

do_install() {
	install -d ${D}${sysconfdir}
	install -m 0640 ${WORKDIR}/sysctl.conf ${D}${sysconfdir}/sysctl.conf
}
