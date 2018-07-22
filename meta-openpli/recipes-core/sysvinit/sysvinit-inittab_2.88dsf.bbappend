DESCRIPTION = "Inittab for sysvinit"

require conf/license/openpli-gplv2.inc

PR = "r6"


S = "${WORKDIR}/sysvinit-${PV}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile() {
	:
}

do_install_append() {
	#install -d ${D}${sysconfdir}
	#install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab
	sed -i 's/id:5:initdefault:/id:3:initdefault:/' ${D}${sysconfdir}/inittab
	printf "\ngui:3:respawn:/usr/bin/enigma2.sh\n" >> ${D}${sysconfdir}/inittab
}
