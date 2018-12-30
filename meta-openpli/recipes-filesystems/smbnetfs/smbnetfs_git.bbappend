FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG = ""
PACKAGECONFIG[gnome-keyring] = ""
PACKAGECONFIG[libsecret] = "--with-libsecret=yes,--with-libsecret=no,libsecret"

SRCREV = "398e8169ea6d3d854af0173b1e66bf13c124f901"

SRC_URI += "file://smbnetfs.common.conf file://smbnetfs.user.conf file://init"

FILES_${PN} += "${sysconfdir}/*.conf ${sysconfdir}/init.d"
CONFFILES_${PN} = "${sysconfdir}/smbnetfs.user.conf"
inherit update-rc.d

INITSCRIPT_NAME = "${PN}.sh"

do_install_append() {
	install -d ${D}${sysconfdir}
	install -m 600 ${WORKDIR}/smbnetfs.common.conf ${D}${sysconfdir}/
	install -m 600 ${WORKDIR}/smbnetfs.user.conf ${D}${sysconfdir}/
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
}

PNBLACKLIST[smbnetfs] = ""
