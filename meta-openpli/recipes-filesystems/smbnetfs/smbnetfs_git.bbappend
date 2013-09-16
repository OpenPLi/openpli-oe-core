FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG = ""

SRC_URI += "file://smbnetfs.conf file://init"

FILES_${PN} += "/home/root/.smb ${sysconfdir}/init.d"

inherit update-rc.d

INITSCRIPT_NAME = "${PN}.sh"

do_install_append() {
	install -d ${D}/home/root/.smb
	install -m 644 ${WORKDIR}/smbnetfs.conf ${D}/home/root/.smb/
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
}
