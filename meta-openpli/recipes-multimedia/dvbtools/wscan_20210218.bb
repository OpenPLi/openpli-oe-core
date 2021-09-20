SUMMARY = "Small command line utility used to perform frequency scans for DVB and ATSC transmissions"
DESCRIPTION = "Wscan is a dvb channel scanner that doesn't require an initial frequency table"
HOMEPAGE = "http://wirbel.htpc-forum.de/w_scan/index2.html"
MAINTAINER = "wirbel <w_scan@gmx-topmail.de>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "https://www.gen2vdr.de/wirbel/w_scan_cpp/w_scan-${PV}.tar.bz2"

SRC_URI[md5sum] = "e80cb986c1830e0ed9b8fe8f6c89809d"
SRC_URI[sha256sum] = "75d7447ebeddfb9ce251f32a93f23190ed46dca44f5b701f2af11e1787b9eb08"

S = "${WORKDIR}/w_scan-${PV}"

inherit autotools

FILES_${PN} += "${datadir}"
