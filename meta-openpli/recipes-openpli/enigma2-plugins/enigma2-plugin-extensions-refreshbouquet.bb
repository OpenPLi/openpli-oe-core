DESCRIPTION = "Actualize services in bouquets."
MAINTAINER = "ims(ims21)"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9432c1f3d564948269193fd19a0ad0fd"

OLDPKG = "${@bb.data.getVar('PN',d,1).replace('-extensions-','-pli-')}"
RREPLACES_${PN} = "${OLDPKG}"
RCONFLICTS_${PN} = "${OLDPKG}"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r0"

DEPENDS += "enigma2"

SRC_URI = "git://github.com/ims21/RefreshBouquet.git;protocol=git"

S="${WORKDIR}/git"

SRCREV = "${AUTOREV}"

inherit distutils-openplugins

PACKAGE_ARCH = "all"


