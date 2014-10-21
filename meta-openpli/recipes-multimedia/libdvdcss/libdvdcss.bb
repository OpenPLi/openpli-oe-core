SUMMARY = "DVD decryption library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=64e753fa7d1ca31632bc383da3b57c27"
PV = "1.3"
PR = "r0"
SRCREV = "b7967b94d3fe8159325269091dedc8b769339cea"

inherit autotools pkgconfig git-project

SRC_URI = "git://git.videolan.org/libdvdcss.git"

EXTRA_OECONF = " --disable-doc "
