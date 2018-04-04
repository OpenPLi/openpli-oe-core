require conf/license/openpli-gplv2.inc
require softcam.inc
inherit cmake

DESCRIPTION = "OScam-emu ${PV} Open Source Softcam"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "svn${SRCPV}"
PKGV = "${PV}"
SRC_URI = "git://github.com/PLi-metas/oscam.git;branch=master"

do_fetch[depends] += "enigma2-plugin-softcams-oscam:do_fetch"

FILESEXTRAPATHS_prepend := "${THISDIR}/enigma2-plugin-softcams-oscam:"
PATCHREV = "2380ba68c25bf7782dab7f5115ccdb0dfda359b1"
PR = "r769"
SRC_URI += "https://raw.githubusercontent.com/oscam-emu/oscam-emu/${PATCHREV}/oscam-emu.patch?${PATCHREV};downloadfilename=oscam-emu.${PATCHREV}.patch;name=emu;striplevel=0"
SRC_URI[emu.md5sum] = "d052a32bccb635e010759c50b8c8a80f"
SRC_URI[emu.sha256sum] = "f21190f68bbe5a4bc45af3bf555bfe1d76bc32db8f0905914b654408216616bd"

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

CONFFILES = "/etc/tuxbox/config/oscam-emu/oscam.conf /etc/tuxbox/config/oscam-emu/oscam.server /etc/tuxbox/config/oscam-emu/oscam.srvid /etc/tuxbox/config/oscam-emu/oscam.user /etc/tuxbox/config/oscam-emu/oscam.provid"

FILES_${PN} = "/usr/bin/oscam-emu /etc/tuxbox/config/oscam-emu/* /etc/init.d/softcam.oscam-emu"

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
	install -d ${D}/etc/tuxbox/config/oscam-emu
	install -m 0644 ${WORKDIR}/oscam.* ${D}/etc/tuxbox/config/oscam-emu
	install -d ${D}/usr/bin
	install -m 0755 ${B}/oscam ${D}/usr/bin/oscam-emu
}
