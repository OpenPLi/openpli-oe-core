SUMMARY = "A Smart Ethernet Switch for Earth"
MAINTAINER = "https://github.com/zerotier/ZeroTierOne"
DESCRIPTION = "Create flat virtual Ethernet networks of almost unlimited size"
HOMEPAGE = "https://www.zerotier.com"
SECTION = "net"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=11bbae9cacaf61dd7fc10035f6f5c68e"

SRC_URI = "git://github.com/zerotier/ZeroTierOne.git;protocol=https;tag=1.10.1 \
	file://zerotier \
	"
SRCREV = "${PV}"

S = "${WORKDIR}/git"

inherit update-rc.d systemd gitpkgv autotools-brokensep

INITSCRIPT_NAME = "zerotier"

TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CFLAGS += "-fpic"

RDEPENDS_${PN} = "kernel-module-tun"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/zerotier ${D}${sysconfdir}/init.d/zerotier
}

INSANE_SKIP_${PN} = "already-stripped"
