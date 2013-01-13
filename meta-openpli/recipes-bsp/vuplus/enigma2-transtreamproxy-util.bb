SUMMARY = "streamproxy manages streaming data to a Mobile device using enigma2"

SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRCDATE = "20101014"
PV = "1.0cvs${SRCDATE}"

SRC_URI = "cvs://anonymous@cvs.schwerkraft.elitedvb.net/cvsroot/streamproxy;module=enigma2-streamproxy;method=pserver \
	file://transcoding.patch;patch=1;pnum=1 \
	"

inherit autotools

S = "${WORKDIR}/enigma2-streamproxy"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/src/streamproxy      ${D}/usr/bin/transtreamproxy
}

