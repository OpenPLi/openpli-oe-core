FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://login-blank-password.patch"

INITSCRIPT_PACKAGES = ""

do_install_append() {
	rm ${D}${sysconfdir}/init.d/vsftpd
	rm ${D}${sysconfdir}/vsftpd.ftpusers
	rm ${D}${sysconfdir}/vsftpd.user_list
}
