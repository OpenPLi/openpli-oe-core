PRINC = "2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Disable tcp-wrappers support (is default in PACKAGECONFIG)
PACKAGECONFIG = ""

# Undo things that we don't want
# Add an "empty" dir
do_install_append() {
	rm -f ${D}${sysconfdir}/vsftpd.ftpusers ${D}${sysconfdir}/vsftpd.user_list
	install -d ${D}${localstatedir}/share/empty
}

PACKAGES =+ "${PN}-initscript"

# Don't use initscripts or useradd (I would like to un-inherit them,
# but there seems to be no way to do that, so introduce a package and
# let them play with that)
FILES_${PN}-initscript = "${sysconfdir}/init.d"
INITSCRIPT_PACKAGES = "${PN}-initscript"
USERADD_PACKAGES = "${PN}-initscript"
USERADD_PARAM_${PN}-initscript = "--system --home-dir /var/lib/ftp --no-create-home -g ftp \
                       --shell /bin/false ftp "
GROUPADD_PARAM_${PN}-initscript = "-r ftp"
