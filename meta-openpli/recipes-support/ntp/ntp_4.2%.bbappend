SUMMARY = "OpenPLi Network Time Protocol daemon and utilities"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# remove the created symlink in the OE recipe
do_install_append() {
	test -L ${D}/${sysconfdir}/network/if-up.d/ntpdate-sync && rm -f ${D}/${sysconfdir}/network/if-up.d/ntpdate-sync
}

# do not create th cron task
pkg_postinst_ntpdate() {
}
