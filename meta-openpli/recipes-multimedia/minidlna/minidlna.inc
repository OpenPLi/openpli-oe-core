DESCRIPTION = "MiniDLNA (aka ReadyDLNA) is server software with the aim of \
being fully compliant with DLNA/UPnP-AV clients."
LICENSE = "GPL-2.0|BSD"
DEPENDS = "ffmpeg flac libav jpeg sqlite3 libexif libogg libid3tag libvorbis"

# because it depends on libav which has commercial flag
LICENSE_FLAGS = "commercial"

inherit gitpkgv

PV = "1.3.1+git${SRCPV}"
PKGV = "1.3.1+git${GITPKGV}"
DEPENDS = "libexif libav libjpeg-turbo libvorbis flac libid3tag sqlite3"

SRC_URI = "git://git.code.sf.net/p/minidlna/git;branch=master;module=git \
           file://0001-add-skript-hu.po-adapt-configure.patch \
           "
S = "${WORKDIR}/git"

do_configure_prepend() {
		sed -i 's/AM_INIT_AUTOMAKE.*$/AM_INIT_AUTOMAKE([foreign subdir-objects])/' ${S}/configure.ac
}

CONFFILES_${PN} = "${sysconfdir}/minidlna.conf"

inherit autotools-brokensep pkgconfig gettext update-rc.d

# DLNAServer plugin will start the service. Only stop by rc.d below.
INITSCRIPT_NAME = "minidlna.sh"
INITSCRIPT_PARAMS = "stop 21 0 1 6 ."

do_install_append() {
	install -m 755 -d ${D}${sysconfdir}/
	install -m 644 ${S}/minidlna.conf ${D}${sysconfdir}/
	install -m 755 -d ${D}${sysconfdir}/init.d/
	install -m 755 ${S}/minidlna.sh ${D}${sysconfdir}/init.d/
	install -m 755 -d ${D}${localstatedir}/lib/minidlna/
}
