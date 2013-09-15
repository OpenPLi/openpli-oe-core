SUMMARY = "Transtreamproxy invokes the hardware transcoding facilities to enable transcoded streaming"

SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "2" 

SRC_URI = "git://schwerkraft.elitedvb.net/streamproxy/streamproxy.git;protocol=git \
	file://transcoding.patch;patch=1;pnum=1 \
	"

inherit autotools

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/src/streamproxy ${D}/usr/bin/transtreamproxy
}
