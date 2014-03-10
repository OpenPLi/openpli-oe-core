DESCRIPTION = "dumpait"
PRIORITY = "required"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.GPLv3;md5=5ed852a46d22220a8b07a68e564d84c7"

PR = "r2"
RREPLACES_${PN} = "vuplus-opera-dumpait"
RCONFLICTS_${PN} = "vuplus-opera-dumpait"

SRVREV = "b83432e0f273738a2957f4a97fab91bd1a034a59"
DESTDIR = "enigma2/python/Plugins/Extensions/HbbTV"
SRC_URI = "git://code.vuplus.com/git/dumpait.git \
           file://fix-gcc47-compile.patch"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/lib/${DESTDIR}
	install -m 0755 ${S}/src/dumpait ${D}/usr/lib/${DESTDIR}
}

do_install_append() {
	rm -rf ${S}
}

FILES_${PN} = "${libdir}/${DESTDIR}/dumpait"

PACKAGES = "${PN}"
