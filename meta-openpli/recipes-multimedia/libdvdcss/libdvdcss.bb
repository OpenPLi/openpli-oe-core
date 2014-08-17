SUMMARY = "DVD decryption library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
PV = "1.3"
PR = "r0"

inherit autotools pkgconfig git-project

SRC_URI = "git://git.videolan.org/libdvdcss.git"
