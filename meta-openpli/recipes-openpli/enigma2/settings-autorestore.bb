PV = "2018102501"
SRC_URI = "file://*"
DESCRIPTION = "Autorecover settings and install packages at first boot from /media/*/backup"
PACKAGES = "${PN}"
MAINTAINER = "OpenPLi team"

require conf/license/openpli-gplv2.inc

# Need to tell bitbake that we have extra files installed
FILES_${PN} = " \
              ${sysconfdir} \
              /bin \
"

S = "${WORKDIR}"

# Not inheriting from rc-update because the script commits suicide, which
# confuses the pkg scripts.
do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -d ${D}/bin
	# run-once initialization script
	install -m 644 ${S}/convert-smbconf.py ${D}/bin/convert-smbconf.py
	install -m 755 ${S}/settings-restore.sh ${D}${sysconfdir}/init.d/settings-restore.sh
	install -m 644 ${S}/convert-smbconf.py ${D}/bin/convert-smbconf.py
}

inherit allarch
