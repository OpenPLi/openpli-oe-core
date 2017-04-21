DESCRIPTION = "Streamproxy for live and file streaming and transcoding"
MAINTAINER = "PLi team"
DEPENDS = "boost"
PACKAGE_ARCH = "${MACHINE_ARCH}"
require conf/license/openpli-gplv2.inc

inherit gitpkgv

PV = "2+git${SRCPV}"
PKGV = "2+git${GITPKGV}"
RDEPENDS_${PN} = "enigma2-plugin-systemplugins-transcodingsetup"

SRC_URI = "git://github.com/eriksl/streamproxy.git;protocol=git"
FILES_${PN} = "/usr/bin/streamproxy /etc/init.d/streamproxy.sh /etc/enigma2/streamproxy.conf"
CONFFILES_${PN} = "/etc/enigma2/streamproxy.conf"
S = "${WORKDIR}/git"

inherit autotools

do_install_append() {
	install -m 755 -d ${D}/etc/init.d/
	install -m 755 ${S}/src/streamproxy.sh ${D}/etc/init.d/
	install -m 755 -d ${D}/etc/enigma2/
	install -m 644 ${S}/src/streamproxy.conf ${D}/etc/enigma2/
}

INITSCRIPT_NAME = "streamproxy.sh"
INITSCRIPT_PARAMS = "defaults 30 70"

inherit update-rc.d
