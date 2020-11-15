SUMMARY = "OpenPLi Network Time Protocol daemon and utilities"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

pkg_prerm_ntpdate() {
    CFGFILE=$D/var/spool/cron/root
    mv $CFGFILE $CFGFILE.tmp
    grep -v "30 * * * *    ${bindir}/ntpdate-sync silent" $CFGFILE.tmp > $CFGFILE
    rm -f $CFGFILE.tmp
    if [ -f ${sysconfdir}/network/if-up.d/ntpdate-sync ]; then
        rm -f ${sysconfdir}/network/if-up.d/ntpdate-sync
    fi
}
