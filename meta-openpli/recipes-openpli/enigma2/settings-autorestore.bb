PV = "20180509"
SRC_URI = "file://*"
DESCRIPTION = "Autorecover settings and install packages at first boot from /media/*/backup"
PACKAGES = "${PN}"
MAINTAINER = "MiLo@OpenPLi"

require conf/license/openpli-gplv2.inc

# Need to tell bitbake that we have extra files installed
FILES_${PN} = "${sysconfdir}"

S = "${WORKDIR}"

# Not inheriting from rc-update because the script commits suicide, which
# confuses the pkg scripts.
do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	# run-once initialization script
	install -m 755 ${S}/settings-restore.sh ${D}${sysconfdir}/init.d/settings-restore.sh
	install -m 755 ${S}/settings-restore.old.sh ${D}${sysconfdir}/init.d/settings-restore.old.sh
	install -m 755 ${S}/autoinstall.sh ${D}${sysconfdir}/init.d/autoinstall.sh
}

inherit allarch
