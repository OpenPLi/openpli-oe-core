require conf/license/openpli-gplv2.inc
require softcam.inc
inherit cmake

DESCRIPTION = "OScam-emu ${PV} Open Source Softcam"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "11385"
PV = "svn${SRCPV}"
PKGV = "${PV}"
SRC_URI = "svn://www.streamboard.tv/svn/oscam;protocol=http;module=trunk;scmdata=keep"

do_fetch[depends] += "enigma2-plugin-softcams-oscam:do_fetch"

FILESEXTRAPATHS_prepend := "${THISDIR}/enigma2-plugin-softcams-oscam:"
SRC_URI += "https://raw.githubusercontent.com/oscam-emu/oscam-emu/fb79f6c3c0846df3a308d4f45da0585f4891c078/oscam-emu.patch;name=emu;striplevel=0"
SRC_URI[emu.md5sum] = "223bb152859d900eae368e1320e7cb2a"
SRC_URI[emu.sha256sum] = "b6552e9009a34511964b1f91f0bacff523331c093742484cd5f73a63e585f47a"

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
	-DHAVE_PCSC=0"

do_install() {
	install -d ${D}/etc/tuxbox/config/oscam-emu
	install -m 0644 ${WORKDIR}/oscam.* ${D}/etc/tuxbox/config/oscam-emu
	install -d ${D}/usr/bin
	install -m 0755 ${B}/oscam ${D}/usr/bin/oscam-emu
}
