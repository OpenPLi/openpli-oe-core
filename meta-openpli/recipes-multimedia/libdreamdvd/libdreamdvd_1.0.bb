SUMMARY = "libdvdnav wrapper for enigma2 based stbs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libdvdnav"
PR = "r3"

inherit autotools pkgconfig git-project

SRCREV = "93f5820499805b4993420eea5d0acb34b61f75a6"
SRC_URI = "git://github.com/mirakels/libdreamdvd.git"
