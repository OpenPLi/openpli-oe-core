DESCRIPTION = "Display line height for fonts used in skin"
MAINTAINER = "ims"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9432c1f3d564948269193fd19a0ad0fd"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/ims21/FontInfo.git;protocol=https"

S="${WORKDIR}/git"

inherit distutils-openplugins
