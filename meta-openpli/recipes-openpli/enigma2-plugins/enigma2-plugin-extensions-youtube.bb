SUMMARY = "Enigma2 plugin to manage your youtube account and watch videos"
DESCRIPTION = "Small plugin to manage your account, search and watch videos \
from youtube"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-youtube"
SECTION = "multimedia"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SRC_URI = "git://github.com/Taapat/enigma2-plugin-youtube.git;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv python3-compileall
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins

RDEPENDS_${PN} = " \
	${PYTHON_PN}-core \
	${PYTHON_PN}-codecs \
	${PYTHON_PN}-json \
	${PYTHON_PN}-netclient \
	${PYTHON_PN}-pyopenssl \
	${PYTHON_PN}-twisted-web \
	"

CONFFILES = "/etc/enigma2/YouTube.key"

do_install_append() {
	install -d ${D}/etc/enigma2
	install -m 0644 ${S}/YouTube.key ${D}/etc/enigma2/YouTube.key
}
