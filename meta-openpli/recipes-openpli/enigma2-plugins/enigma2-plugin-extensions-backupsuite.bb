DESCRIPTION = "Backup Suite"
LICENSE = "GPLv3"
AUTHOR = "Pedro Newbie <pedro.newbie@gmail.com>"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = "git://github.com/persianpros/BackupSuite-PLi.git;protocol=https \
           file://use-pyc-instead-of-pyo.patch \
"

# don't inherit allarch, it can't work with arch-dependent RDEPENDS
inherit gitpkgv distutils-openplugins gettext

RDEPENDS_${PN} = " \
	mtd-utils \
	mtd-utils-ubifs \
	ofgwrite \
	${@bb.utils.contains("IMAGE_FSTYPES", "tar.bz2", "bzip2" , "", d)} \
	"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

do_install_append() {
	find "${D}" -name '*.sh' -exec chmod a+x '{}' ';'
}

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
