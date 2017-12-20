DESCRIPTION = "Backup Suite"
LICENSE = "GPLv3"
MAINTAINER = "Persian Professionals"
AUTHOR = "Pedro Newbie <pedro.newbie@gmail.com>"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = "git://github.com/persianpros/BackupSuite.git;protocol=git"

inherit gitpkgv allarch distutils-openplugins

S = "${WORKDIR}/git"

PV = "22+git${SRCPV}"
PKGV = "22+git${GITPKGV}"

RDEPENDS_${PN} = "mtd-utils mtd-utils-ubifs ofgwrite"
