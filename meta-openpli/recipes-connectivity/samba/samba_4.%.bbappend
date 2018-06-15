# version
PR = "r3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Remove acl, cups etc. support.
PACKAGECONFIG_remove = "acl cups"

EXTRA_OECONF += " \
                 --without-cluster-support \
                 --without-profiling-data \
                 --with-sockets-dir=${localstatedir}/run \
                 --with-logfilebase=${localstatedir}/log/samba \
                 --with-pam \
                 --with-pam_smbpass \
                 --nopyc \
                 --disable-iprint \
                 --without-ad-dc \
                 --without-dnsupdate \
                 --without-quotas \
                 --without-winbind \
                 --without-syslog \
                 --disable-python \
                "

EXTRA_OECONF_remove = " \
                       --with-cluster-support \
                       --with-profiling-data \
                       --with-sockets-dir=/run/samba \
                      "

SRC_URI += " \
           file://smb.conf \
           file://smb-secure.conf \
           file://smb-user.conf \
           file://pam.config \
           file://samba.sh \
           file://users.map \
           file://smbpasswd \
           file://share.template \
           file://0017-Revert-pam_smbpass-REMOVE-this-PAM-module.patch \
           file://0018-Revert-source3-wscript-remove-pam_smbpass-option-as-it-was-removed.patch \
           file://0019-dynamically-create-a-samba-account-if-needed.patch \
           "

FILES_${PN}-base += " \
                    ${sysconfdir}/samba/smb.conf \
                    ${sysconfdir}/samba/smb-secure.conf \
                    ${sysconfdir}/samba/shares/share.template \
                    ${sysconfdir}/init.d/samba.sh \
                    ${bindir}/testparm \
                    ${bindir}/smbpasswd \
                    ${bindir}/smbstatus \
                    "

CONFFILES_${PN}-base += " \
                        ${sysconfdir}/samba/smb-user.conf \
                        ${sysconfdir}/samba/shares/share.template \
                        "

# move smbpass config files to samba-common
FILES_${BPN}-common += " \
                      ${sysconfdir}/pam.d/samba \
                      ${sysconfdir}/samba/private/users.map \
                      ${sysconfdir}/samba/private/smbpasswd \
                      "

CONFFILES_${BPN}-common += " \
                          ${sysconfdir}/pam.d/samba \
                          ${sysconfdir}/samba/private/users.map \
                          ${sysconfdir}/samba/private/smbpasswd \
                          "

RPROVIDES_${PN} += "pam-pluginsmbpass"
RRECOMMENDS_${PN}-base+= "wsdd pam-pluginsmbpass"

do_install_prepend() {
	install -d ${D}${sysconfdir}/sudoers.d
}

do_configure_prepend() {
	perl -i -pe 's#lp_private_dir#lp_pid_directory#' ${S}/source3/lib/messages.c
}

do_install_append() {
	rm -fR ${D}/var
	rm -fR ${D}/run
	rm -fR ${D}${sysconfdir}/tmpfiles.d
	rm -fR ${D}${sysconfdir}/sysconfig
	rm -f ${D}${sysconfdir}/init.d/samba
	install -d ${D}${sysconfdir}/pam.d
	install -m 644 ${WORKDIR}/pam.config ${D}${sysconfdir}/pam.d/samba
	install -d ${D}${sysconfdir}/samba
	install -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba
	install -m 644 ${WORKDIR}/smb-secure.conf ${D}${sysconfdir}/samba
	install -m 644 ${WORKDIR}/smb-user.conf ${D}${sysconfdir}/samba
	touch ${D}${sysconfdir}/samba/smb-shares.conf
	install -m 755 ${WORKDIR}/samba.sh ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/samba/shares
	install -m 644 ${WORKDIR}/share.template ${D}${sysconfdir}/samba/shares
	install -d ${D}${sysconfdir}/samba/private
	install -m 644 ${WORKDIR}/users.map ${D}${sysconfdir}/samba/private
	install -m 644 ${WORKDIR}/smbpasswd ${D}${sysconfdir}/samba/private
}

pkg_postinst_${BPN}-common_prepend() {
#!/bin/sh

if [ -z "$D" ]; then
	# make sure we have the root user in smbpasswd
	[ -e /etc/samba/private/smbpasswd ] || touch /etc/samba/private/smbpasswd
	grep -qE '^root:' /etc/samba/private/smbpasswd
	if [[ $? -ne 0 ]] ; then
		smbpasswd -Lan root >/dev/null
	fi
fi

# add smbpass support to pam.d
grep -v "pam_smbpass.so" $D/etc/pam.d/common-password > $D/tmp/common-password
printf "password\toptional\t\t\tpam_smbpass.so nullok use_authtok use_first_pass\n" >> $D/tmp/common-password
mv $D/tmp/common-password $D/etc/pam.d/common-password
}

pkg_prerm_${BPN}-common() {
#!/bin/sh

# remove smbpass support from pam.d
grep -v "pam_smbpass.so" /etc/pam.d/common-password > /tmp/common-password
mv /tmp/common-password /etc/pam.d/common-password
}

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}-base"
INITSCRIPT_NAME_${PN}-base = "samba.sh"
INITSCRIPT_PARAMS_${PN}-base = "defaults"

# remove libnetapi package witch contains a lot of cross dependencies from libsamba-base
PACKAGES_remove = "libnetapi"

# update libsamba-base libraries for samba 4.4.5 to fix circular dependencies
FILES_lib${BPN}-base = "\
                    ${libdir}/libdcerpc-binding.so.0.0.1 \
                    ${libdir}/libndr-krb5pac.so.0.0.1 \
                    ${libdir}/libndr-nbt.so.0.0.1 \
                    ${libdir}/libndr-standard.so.0.0.1 \
                    ${libdir}/libndr.so.0.0.5 \
                    ${libdir}/libnetapi.so.0 \
                    ${libdir}/libsamba-credentials.so.0.0.1 \
                    ${libdir}/libsamba-errors.so.1 \
                    ${libdir}/libsamba-hostconfig.so.0.0.1 \
                    ${libdir}/libsamba-passdb.so.0.25.0 \
                    ${libdir}/libsamba-util.so.0.0.1 \
                    ${libdir}/libsamdb.so.0.0.1 \
                    ${libdir}/libsmbconf.so.0 \
                    ${libdir}/libsmbldap.so.0 \
                    ${libdir}/libtevent-unix-util.so.0.0.1 \
                    ${libdir}/libtevent-util.so.0.0.1 \
                    ${libdir}/security/pam_smbpass.so \
                    ${libdir}/samba/libCHARSET3-samba4.so \
                    ${libdir}/samba/libaddns-samba4.so \
                    ${libdir}/samba/libads-samba4.so \
                    ${libdir}/samba/libasn1util-samba4.so \
                    ${libdir}/samba/libauth-sam-reply-samba4.so \
                    ${libdir}/samba/libauth-samba4.so \
                    ${libdir}/samba/libauthkrb5-samba4.so \
                    ${libdir}/samba/libcli-cldap-samba4.so \
                    ${libdir}/samba/libcli-ldap-common-samba4.so \
                    ${libdir}/samba/libcli-nbt-samba4.so \
                    ${libdir}/samba/libcli-smb-common-samba4.so \
                    ${libdir}/samba/libcli-spoolss-samba4.so \
                    ${libdir}/samba/libcliauth-samba4.so \
                    ${libdir}/samba/libdbwrap-samba4.so \
                    ${libdir}/samba/libdcerpc-samba-samba4.so \
                    ${libdir}/samba/libflag-mapping-samba4.so \
                    ${libdir}/samba/libgenrand-samba4.so \
                    ${libdir}/samba/libgensec-samba4.so \
                    ${libdir}/samba/libgse-samba4.so \
                    ${libdir}/samba/libinterfaces-samba4.so \
                    ${libdir}/samba/libiov-buf-samba4.so \
                    ${libdir}/samba/libkrb5samba-samba4.so \
                    ${libdir}/samba/libldbsamba-samba4.so \
                    ${libdir}/samba/liblibcli-lsa3-samba4.so \
                    ${libdir}/samba/liblibcli-netlogon3-samba4.so \
                    ${libdir}/samba/liblibsmb-samba4.so \
                    ${libdir}/samba/libmessages-dgm-samba4.so \
                    ${libdir}/samba/libmessages-util-samba4.so \
                    ${libdir}/samba/libmsghdr-samba4.so \
                    ${libdir}/samba/libmsrpc3-samba4.so \
                    ${libdir}/samba/libndr-samba-samba4.so \
                    ${libdir}/samba/libndr-samba4.so \
                    ${libdir}/samba/libnpa-tstream-samba4.so \
                    ${libdir}/samba/libpopt-samba3-samba4.so \
                    ${libdir}/samba/libprinting-migrate-samba4.so \
                    ${libdir}/samba/libreplace-samba4.so \
                    ${libdir}/samba/libsamba-cluster-support-samba4.so \
                    ${libdir}/samba/libsamba-debug-samba4.so \
                    ${libdir}/samba/libsamba-modules-samba4.so \
                    ${libdir}/samba/libsamba-security-samba4.so \
                    ${libdir}/samba/libsamba-sockets-samba4.so \
                    ${libdir}/samba/libsamba3-util-samba4.so \
                    ${libdir}/samba/libsamdb-common-samba4.so \
                    ${libdir}/samba/libsecrets3-samba4.so \
                    ${libdir}/samba/libserver-id-db-samba4.so \
                    ${libdir}/samba/libserver-role-samba4.so \
                    ${libdir}/samba/libsmb-transport-samba4.so \
                    ${libdir}/samba/libsmbd-base-samba4.so \
                    ${libdir}/samba/libsmbd-conn-samba4.so \
                    ${libdir}/samba/libsmbd-shim-samba4.so \
                    ${libdir}/samba/libsmbregistry-samba4.so \
                    ${libdir}/samba/libsocket-blocking-samba4.so \
                    ${libdir}/samba/libsys-rw-samba4.so \
                    ${libdir}/samba/libtalloc-report-samba4.so \
                    ${libdir}/samba/libtdb-wrap-samba4.so \
                    ${libdir}/samba/libtime-basic-samba4.so \
                    ${libdir}/samba/libutil-cmdline-samba4.so \
                    ${libdir}/samba/libutil-reg-samba4.so \
                    ${libdir}/samba/libutil-setid-samba4.so \
                    ${libdir}/samba/libutil-tdb-samba4.so \
                    ${libdir}/samba/libwinbind-client-samba4.so \
                    ${libdir}/samba/pdb/tdbsam.so \
"

# move some libraries from libsamba-base to libwbclient to fix circular dependencies
FILES_libwbclient = "${libdir}/libwbclient.so.* \
                     ${libdir}/samba/libwinbind-client.so \
                     ${libdir}/samba/libwinbind-client-samba4.so \
                     ${libdir}/samba/libreplace-samba4.so \
"
