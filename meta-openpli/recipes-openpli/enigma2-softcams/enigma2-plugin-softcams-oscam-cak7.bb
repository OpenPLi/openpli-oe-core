require conf/license/openpli-gplv2.inc
require softcam.inc
inherit cmake
inherit gitpkgv

DESCRIPTION = "OScam-cak7 ${PV} Open Source Softcam"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

FILESEXTRAPATHS:prepend := "${THISDIR}/enigma2-plugin-softcams-oscam:"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

SRC_ORIGIN ?= "git://repo.or.cz/oscam.git;protocol=git;branch=master"
SRC_URI := "${SRC_ORIGIN} \
	"

DEPENDS = "libusb openssl"
RRECOMMENDS:${PN} += "enigma2-plugin-extensions-oscamstatus"

LDFLAGS:prepend = "-ludev "

S = "${WORKDIR}/git"
B = "${S}"
CAMNAME = "oscam-cak7"
CAMSTART = "/usr/bin/oscam-cak7 --wait 60 --config-dir /etc/tuxbox/config/oscam-cak7 --daemon --pidfile /tmp/oscam-cak7.pid --restart 2 --utf8"
CAMSTOP = "kill \`cat /tmp/oscam-cak.pid\` 2> /dev/null"

SRC_URI += " \
	file://oscam.conf \
	file://oscam.server \
	file://oscam.srvid \
	file://oscam.user \
	file://oscam.dvbapi \
	file://oscam.provid"

CONFFILES = "${sysconfdir}/tuxbox/config/oscam-cak7/oscam.conf ${sysconfdir}/tuxbox/config/oscam-cak7/oscam.server ${sysconfdir}/tuxbox/config/oscam-cak7/oscam.srvid ${sysconfdir}/tuxbox/config/oscam-cak7/oscam.user ${sysconfdir}/tuxbox/config/oscam-cak7/oscam.dvbapi ${sysconfdir}/tuxbox/config/oscam-cak7/oscam.provid"

FILES:${PN} = "${bindir}/oscam-cak7 ${sysconfdir}/tuxbox/config/oscam-cak7/* ${sysconfdir}/init.d/softcam.oscam-cak7"

EXTRA_OECMAKE += "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=0 \
	-DHAVE_LIBUSB=1 \
	-DSTATIC_LIBUSB=1 \
	-DWITH_SSL=1 \
	-DIPV6SUPPORT=1 \
	-DCLOCKFIX=1 \
	-DHAVE_PCSC=1 \
	-DCARDREADER_SMARGO=1 \
	-DCARDREADER_PCSC=1 \
	-DCW_CYCLE_CHECK=1 \
	-DCS_CACHEEX=1 \
	-DMODULE_CONSTCW=1 \
	-DLCDSUPPORT=1 \
	"

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/config/oscam-cak7
	install -m 0644 ${WORKDIR}/oscam.* ${D}${sysconfdir}/tuxbox/config/oscam-cak7/
	install -d ${D}${bindir}
	install -m 0755 ${B}/oscam ${D}${bindir}/oscam-cak7
}
