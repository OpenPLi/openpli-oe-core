SUMMARY = "OpenPLi Network Time Protocol daemon and utilities"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

pkg_postinst_ntpdate() {
    if ! grep -q -s ntpdate $D/var/spool/cron/crontabs/root; then
        echo "adding crontab"
        test -d $D/var/spool/cron/crontabs || mkdir -p $D/var/spool/cron/crontabs
        echo "30 * * * *    ${bindir}/ntpdate-sync silent" >> $D/var/spool/cron/crontabs/root
    fi
}
