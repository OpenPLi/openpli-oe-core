require conf/license/openpli-gplv2.inc
require softcam.inc
inherit cmake
inherit gitpkgv

DESCRIPTION = "OScam-emu ${PV} Open Source Softcam"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

FILESEXTRAPATHS_prepend := "${THISDIR}/enigma2-plugin-softcams-oscam:"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
SRC_URI = "git://github.com/oscam-emu/oscam-patched.git;protocol=https"

DEPENDS = "libusb openssl"

S = "${WORKDIR}/git"
B = "${S}"
CAMNAME = "oscam-emu"
CAMSTART = "/usr/bin/oscam-emu --config-dir /etc/tuxbox/config/oscam-emu --daemon --pidfile /tmp/oscam-emu.pid --restart 2 --utf8"
CAMSTOP = "kill \`cat /tmp/oscam-emu.pid\` 2> /dev/null"

SRC_URI += " \
	file://oscam.conf \
	file://oscam.server \
	file://oscam.srvid \
	file://oscam.user \
	file://oscam.provid"

CONFFILES = "${sysconfdir}/tuxbox/config/oscam-emu/oscam.conf ${sysconfdir}/tuxbox/config/oscam-emu/oscam.server ${sysconfdir}/tuxbox/config/oscam-emu/oscam.srvid ${sysconfdir}/tuxbox/config/oscam-emu/oscam.user ${sysconfdir}/tuxbox/config/oscam-emu/oscam.provid"

FILES_${PN} = "${bindir}/oscam-emu ${sysconfdir}/tuxbox/config/oscam-emu/* ${sysconfdir}/init.d/softcam.oscam-emu"

EXTRA_OECMAKE += "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=0 \
	-DHAVE_LIBUSB=1 \
	-DSTATIC_LIBUSB=1 \
	-DWITH_SSL=1 \
	-DIPV6SUPPORT=1 \
	-DCLOCKFIX=0 \
	-DHAVE_PCSC=0"

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/config/oscam-emu
	install -m 0644 ${WORKDIR}/oscam.* ${D}${sysconfdir}/tuxbox/config/oscam-emu
	install -d ${D}${bindir}
	install -m 0755 ${B}/oscam ${D}${bindir}/oscam-emu
}
