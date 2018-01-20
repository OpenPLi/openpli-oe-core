DESCRIPTION = "Backup Suite"
LICENSE = "GPLv3"
MAINTAINER = "Persian Professionals"
AUTHOR = "Pedro Newbie <pedro.newbie@gmail.com>"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = "git://github.com/persianpros/BackupSuite.git;protocol=git"

# don't inherit allarch, it can't work with arch-dependent RDEPENDS
inherit gitpkgv distutils-openplugins gettext

RDEPENDS_${PN} = "mtd-utils mtd-utils-ubifs ofgwrite"

S = "${WORKDIR}/git"

PV = "24+git${SRCPV}"
PKGV = "24+git${GITPKGV}"

do_install_append() {
	find "${D}" -name '*.sh' -exec chmod a+x '{}' ';'
}
