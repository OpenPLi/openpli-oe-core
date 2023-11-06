DESCRIPTION = "Plugin for managing autofs files, mount and umount"
MAINTAINER = "ims"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9432c1f3d564948269193fd19a0ad0fd"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/ims21/ManagerAutofs.git;protocol=https;branch=master"

S="${WORKDIR}/git"

inherit distutils-openplugins
