SUMMARY = "Small command line utility used to perform frequency scans for DVB and ATSC transmissions"
DESCRIPTION = "Wscan is a dvb channel scanner that doesn't require an initial frequency table"
HOMEPAGE = "http://wirbel.htpc-forum.de/w_scan/index2.html"
MAINTAINER = "wirbel <w_scan@gmx-topmail.de>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "http://wirbel.htpc-forum.de/w_scan/w_scan-${PV}.tar.bz2"

SRC_URI[md5sum] = "da0f190bee696a02bf030fc01c0706e8"
SRC_URI[sha256sum] = "b6d7c9ab997c53a0b0d92e8390f313cc3b82ee8ece1756b4e526119fd5ba09b4"

S = "${WORKDIR}/w_scan-${PV}"

inherit autotools

FILES_${PN} += "${datadir}"
