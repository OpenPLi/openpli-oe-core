SUMMARY = "Very Secure FTP server"
HOMEPAGE = "https://security.appspot.com/vsftpd.html"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6067ad950b28336613aed9dd47b1271"
PR = "r1"

DEPENDS = "libcap openssl"

SRC_URI = "https://security.appspot.com/downloads/vsftpd-${PV}.tar.gz \
           file://makefile-destdir.patch \
           file://makefile-libs.patch \
           file://makefile-strip.patch \
           file://vsftpd.conf \
           file://login-blank-password.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=a6067ad950b28336613aed9dd47b1271 \
                        file://COPYRIGHT;md5=04251b2eb0f298dae376d92454f6f72e \
                        file://LICENSE;md5=654df2042d44b8cac8a5654fc5be63eb"
SRC_URI[md5sum] = "da119d084bd3f98664636ea05b5bb398"
SRC_URI[sha256sum] = "9d4d2bf6e6e2884852ba4e69e157a2cecd68c5a7635d66a3a8cf8d898c955ef7"

PACKAGECONFIG ??= ""
PACKAGECONFIG[tcp-wrappers] = ",,tcp-wrappers"
SRC_URI +="${@bb.utils.contains('PACKAGECONFIG', 'tcp-wrappers', 'file://vsftpd-tcp_wrappers-support.patch', '', d)}"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam-plugin-listfile', '', d)}"
PAMLIB = "${@bb.utils.contains('DISTRO_FEATURES', 'pam', '-L${STAGING_BASELIBDIR} -lpam', '', d)}"
WRAPLIB = "${@bb.utils.contains('PACKAGECONFIG', 'tcp-wrappers', '-lwrap', '', d)}"
NOPAM_SRC ="${@bb.utils.contains('PACKAGECONFIG', 'tcp-wrappers', 'file://nopam-with-tcp_wrappers.patch', 'file://nopam.patch', d)}"
SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'pam', '', '${NOPAM_SRC}', d)}"

CONFFILES_${PN} = "${sysconfdir}/vsftpd.conf"
LDFLAGS_append =" -lcrypt -lcap"

EXTRA_OEMAKE = "-e "

do_configure() {
    # Fix hardcoded /usr, /etc, /var mess.
    cat tunables.c|sed s:\"/usr:\"${prefix}:g|sed s:\"/var:\"${localstatedir}:g \
    |sed s:\"${prefix}/share/empty:\"${localstatedir}/share/empty:g |sed s:\"/etc:\"${sysconfdir}:g > tunables.c.new
    mv tunables.c.new tunables.c
}

do_compile() {
   oe_runmake "LIBS=-L${STAGING_LIBDIR} -lcrypt -lcap ${PAMLIB} ${WRAPLIB}"
}

do_install() {
    install -d ${D}${sbindir}
    install -d ${D}${mandir}/man8
    install -d ${D}${mandir}/man5
    oe_runmake 'DESTDIR=${D}' install
    install -d ${D}${sysconfdir}
    install -m 600 ${WORKDIR}/vsftpd.conf ${D}${sysconfdir}/vsftpd.conf
    if ! test -z ${PAMLIB} ; then
        install -d ${D}${sysconfdir}/pam.d/
        cp ${S}/RedHat/vsftpd.pam ${D}${sysconfdir}/pam.d/vsftpd
        sed -i "s:/lib/security:${base_libdir}/security:" ${D}${sysconfdir}/pam.d/vsftpd
        sed -i "s:ftpusers:vsftpd.ftpusers:" ${D}${sysconfdir}/pam.d/vsftpd
    fi

	install -d ${D}${localstatedir}/share/empty
}
