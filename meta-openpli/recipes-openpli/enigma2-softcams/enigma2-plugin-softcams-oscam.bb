require conf/license/openpli-gplv2.inc
require softcam.inc
inherit cmake
inherit gitpkgv

DESCRIPTION = "OScam ${PV} Open Source Softcam"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
SRC_URI = "git://github.com/OpenVisionE2/oscam.git;protocol=https"

DEPENDS = "libusb openssl"
RRECOMMENDS_${PN} += "enigma2-plugin-extensions-oscamstatus"

LDFLAGS_prepend = "-ludev "

S = "${WORKDIR}/git"
B = "${S}"
CAMNAME = "oscam"
CAMSTART = "/usr/bin/oscam --wait 60 --config-dir /etc/tuxbox/config/oscam --daemon --pidfile /tmp/oscam.pid --restart 2 --utf8"
CAMSTOP = "kill \`cat /tmp/oscam.pid\` 2> /dev/null"

SRC_URI += " \
	file://oscam.conf \
	file://oscam.server \
	file://oscam.srvid \
	file://oscam.user \
	file://oscam.dvbapi \
	file://oscam.provid"

CONFFILES = "${sysconfdir}/tuxbox/config/oscam/oscam.conf ${sysconfdir}/tuxbox/config/oscam/oscam.server ${sysconfdir}/tuxbox/config/oscam/oscam.srvid ${sysconfdir}/tuxbox/config/oscam/oscam.user ${sysconfdir}/tuxbox/config/oscam/oscam.dvbapi ${sysconfdir}/tuxbox/config/oscam/oscam.provid"

FILES_${PN} = "${bindir}/oscam ${sysconfdir}/tuxbox/config/oscam/* ${sysconfdir}/init.d/softcam.oscam"

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
	install -d ${D}${sysconfdir}/tuxbox/config/oscam
	install -m 0644 ${WORKDIR}/oscam.* ${D}${sysconfdir}/tuxbox/config/oscam/
	install -d ${D}${bindir}
	install -m 0755 ${B}/oscam ${D}${bindir}
}
