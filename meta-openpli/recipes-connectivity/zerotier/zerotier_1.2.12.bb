SUMMARY = "A Smart Ethernet Switch for Earth"
MAINTAINER = "https://github.com/zerotier/ZeroTierOne"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=886557d0c9eee76bfbb292c1e01e2f43"

SRC_URI = "git://github.com/zerotier/ZeroTierOne.git;protocol=http"
SRCREV = "${PV}"

S = "${WORKDIR}/git"

inherit gitpkgv autotools-brokensep

TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CFLAGS += "-fpic"
