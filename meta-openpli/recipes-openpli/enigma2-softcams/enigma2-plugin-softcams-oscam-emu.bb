require conf/license/openpli-gplv2.inc
require softcam.inc
inherit cmake

DESCRIPTION = "OScam-emu ${PV} Open Source Softcam"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "svn${SRCPV}"
PKGV = "${PV}"
SRC_URI = "svn://www.streamboard.tv/svn/oscam;protocol=http;module=trunk;scmdata=keep"

do_fetch[depends] += "enigma2-plugin-softcams-oscam:do_fetch"

FILESEXTRAPATHS_prepend := "${THISDIR}/enigma2-plugin-softcams-oscam:"
PATCHREV = "4db2944c6f1c943cc113ba57fcb6b08c7d9fd375"
PR = "r766"
SRC_URI += "https://raw.githubusercontent.com/oscam-emu/oscam-emu/${PATCHREV}/oscam-emu.patch?${PATCHREV};downloadfilename=oscam-emu.${PATCHREV}.patch;name=emu;striplevel=0"
SRC_URI[emu.md5sum] = "1a3b666e8f5837255421a789d503e1cc"
SRC_URI[emu.sha256sum] = "99092052f6267c1f11168e5ca40401ac99afbca06dd421967766d04e86ecf1b5"

DEPENDS = "libusb openssl"

S = "${WORKDIR}/trunk"
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
