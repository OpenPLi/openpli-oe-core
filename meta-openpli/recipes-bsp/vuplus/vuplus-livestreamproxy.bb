DESCRIPTION = "Livestream transcoding proxy"
SUMMARY = "Program to feed transcoded streams to http clients"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "1"

PACKAGE_ARCH := "${MACHINE_ARCH}"

RREPLACES_${PN} = "enigma2-transtreamproxy-util enigma2-plugin-systemplugins-transcodingsetup"
RCONFLICTS_${PN} = "enigma2-transtreamproxy-util enigma2-plugin-systemplugins-transcodingsetup"

SRC_URI = "git://code.vuplus.com/git/filestreamproxy.git;protocol=git;branch=transtreamproxy"

FILES_${PN} = "${bindir}/livestreamproxy"

inherit autotools

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/src/transtreamproxy ${D}/usr/bin/livestreamproxy
}

pkg_postinst_${PN} () {
	rm -rf $$-1
	egrep -v '^8002[ \t]+' /etc/inetd.conf > /tmp/$$-1
	cat /tmp/$$-1 > /etc/inetd.conf
	echo "8002 stream tcp nowait root /usr/bin/livestreamproxy livestreamproxy" >> /etc/inetd.conf
	rm -rf $$-1
	killall -HUP inetd
}

pkg_postrm_${PN} () {
	rm -rf $$-1
	egrep -v '^8002[ \t]+' /etc/inetd.conf > /tmp/$$-1
	cat /tmp/$$-1 >| /etc/inetd.conf
	rm -rf $$-1
	killall -HUP inetd
}
