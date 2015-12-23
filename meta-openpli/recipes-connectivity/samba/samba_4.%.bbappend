
# Remove acl, cups etc. support.
PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SYSVINITTYPE}', '', d)} \
                 ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
                 ${@base_contains('DISTRO_FEATURES', 'zeroconf', 'zeroconf', '', d)} \
                 aio \
                "

EXTRA_OECONF += " \
                 --without-cluster-support \
                 --without-profiling-data \
                 --with-sockets-dir=${localstatedir}/run \
                 --with-logfilebase=${localstatedir}/log \
                "

EXTRA_OECONF_remove = " \
                       --with-cluster-support \
                       --with-profiling-data \
                       --with-sockets-dir=${localstatedir}/run/samba \
                      "

# Fix typos
PACKAGECONFIG[acl] = "--with-acl-support,--without-acl-support,acl"
PACKAGECONFIG[aio] = "--with-aio-support,--without-aio-support,libaio"

# Remove unused, add own config, init script
SRC_URI += " \
           file://smb.conf \
           file://samba.sh \
           "

FILES_${PN} += "${sysconfdir}/init.d/samba.sh"

do_install_append() {
	rm -fR ${D}/var
	rm -fR ${D}/run
	rm -fR ${D}${bindir}
	rm -fR ${D}${libdir}/tmpfiles.d
	rm -fR ${D}${sysconfdir}/sysconfig
	cp -f ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/samba.sh ${D}${sysconfdir}/init.d
}

INITSCRIPT_NAME = "samba.sh"
INITSCRIPT_PARAMS = "defaults"

inherit update-rc.d

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

