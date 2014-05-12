DESCRIPTION = "File transcoding proxy"
SUMMARY = "Program to feed transcoded files to http clients"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "2"

PACKAGE_ARCH := "${MACHINE_ARCH}"

RCONFLICTS_${PN} = "enigma2-transtreamproxy-util enigma2-plugin-systemplugins-transcodingsetup"

SRC_URI = "git://code.vuplus.com/git/filestreamproxy.git;protocol=git;branch=master \
	file://0001-missing-includes.patch;striplevel=1;apply=yes"

FILES_${PN} = "${bindir}/filestreamproxy"

inherit autotools

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/src/filestreamproxy ${D}/usr/bin/filestreamproxy
}

pkg_postinst_${PN} () {
	rm -rf $$-1
	egrep -v '^8003[ \t]+' /etc/inetd.conf > /tmp/$$-1
	cat /tmp/$$-1 >| /etc/inetd.conf
	echo "8003 stream tcp nowait root /usr/bin/filestreamproxy filestreamproxy" >> /etc/inetd.conf
	rm -rf $$-1
	killall -HUP inetd
}

pkg_postrm_${PN} () {
	rm -rf $$-1
	egrep -v '^8003[ \t]+' /etc/inetd.conf > /tmp/$$-1
	cat /tmp/$$-1 >| /etc/inetd.conf
	rm -rf $$-1
	killall -HUP inetd
}
