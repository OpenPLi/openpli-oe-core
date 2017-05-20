DESCRIPTION = "Set fallback remote receiver from editable remote receivers list"
MAINTAINER = "ims"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=534862957bf314f95d85e0c07632f84d"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/ims21/FallbackReceiver.git;protocol=git"

S="${WORKDIR}/git"

inherit distutils-openplugins
