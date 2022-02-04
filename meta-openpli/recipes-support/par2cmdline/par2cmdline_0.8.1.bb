DESCRIPTION = "A tool to apply the data-recovery capability concepts of RAID-like systems \
to the posting & recovery of multi-part archives on Usenet."
MAINTAINER = "Peter Brian Clements <peterbclements@users.sourceforge.net>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

HOMEPAGE = "http://parchive.sourceforge.net/"
DEPENDS = "libsigc++-3"

SRC_URI = "https://github.com/Parchive/par2cmdline/releases/download/v${PV}/${BPN}-${PV}.tar.gz \
	"

inherit autotools pkgconfig

SRC_URI[md5sum] = "416869e09b401114ff4afc92d7028751"
SRC_URI[sha256sum] = "7b2fcc19b54c7080939fc2cbaca33ae40ac33759a526292933c94a85ba850d11"

