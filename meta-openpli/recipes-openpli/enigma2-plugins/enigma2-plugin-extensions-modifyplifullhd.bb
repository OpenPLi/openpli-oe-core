DESCRIPTION = "Modify colors anf font in PLi-FullHD and PLi-HD1"
MAINTAINER = "ims"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=534862957bf314f95d85e0c07632f84d"

OLDPKG = "${@bb.data.getVar('PN',d,1).replace('-extensions-','-pli-')}"
RREPLACES_${PN} = "${OLDPKG}"
RCONFLICTS_${PN} = "${OLDPKG}"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r0"

DEPENDS += "enigma2"

SRC_URI = "git://github.com/ims21/ModifyPliFullHD.git;protocol=git"

S="${WORKDIR}/git"

SRCREV = "${AUTOREV}"

inherit distutils-openplugins

PACKAGE_ARCH = "all"


