FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

EXTRA_OECONF += "--with-confdir=/etc/default"

SRC_URI += " file://99_autofs"

CONFFILES = "/etc/auto.master /etc/auto.net"

# Remove and change configuration files
do_install_append() {
	echo "/media/net /etc/auto.net --ghost" > ${D}/etc/auto.master
	echo "# automounter configuration" > ${D}/etc/auto.net
	chmod 0644 ${D}/etc/auto.net
	rm -f ${D}/etc/auto.smb ${D}/etc/auto.misc ${D}/etc/autofs_ldap_auth.conf
	sed -i 's/^TIMEOUT=300/TIMEOUT=30/' ${D}/etc/default/autofs
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 644 ${WORKDIR}/99_autofs ${D}${sysconfdir}/default/volatiles/99_autofs
}

pkg_postinst_${PN} () {
        if [ -z "$D" ]; then
                if [ -e ${sysconfdir}/init.d/populate-volatile.sh ]; then
                        ${sysconfdir}/init.d/populate-volatile.sh update
                fi
        fi
}
