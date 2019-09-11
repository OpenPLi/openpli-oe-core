DESCRIPTION = "MiniDLNA a DLNA/UPnP-AV server"
SUMMARY = "Please use the DLNAServer plugin, enigma2-plugin-extensions-dlnaserver."
HOMEPAGE = "http://github.com/OpenVisionE2/minidlna"
SECTION = "network"
MAINTAINER = "Open Vision Developers"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENCE.miniupnpd;md5=b0dabf9d8e0f871554e309d62ead8d2b"

inherit gitpkgv

PV = "1.2.2+git${SRCPV}"
PKGV = "1.2.2+git${GITPKGV}"
DEPENDS = "libexif libav libjpeg-turbo libvorbis flac libid3tag sqlite3"

SRC_URI = "git://github.com/OpenVisionE2/minidlna;protocol=https;branch=master"

S = "${WORKDIR}/git"

do_configure_prepend() {
		sed -i 's/AM_INIT_AUTOMAKE.*$/AM_INIT_AUTOMAKE([foreign subdir-objects])/' ${S}/configure.ac
}

CONFFILES_${PN} = "/etc/minidlna.conf"

inherit autotools-brokensep pkgconfig gettext update-rc.d

# DLNAServer plugin will start the service. Only stop by rc.d below.
INITSCRIPT_NAME = "minidlna.sh"
INITSCRIPT_PARAMS = "stop 21 0 1 6 ."

do_install_append() {
	install -m 755 -d ${D}/${sysconfdir}/
	install -m 644 ${S}/minidlna.conf ${D}/${sysconfdir}/
	install -m 755 -d ${D}/${sysconfdir}/init.d/
	install -m 755 ${S}/minidlna.sh ${D}/${sysconfdir}/init.d/
	install -m 755 -d ${D}/var/lib/minidlna/
}
