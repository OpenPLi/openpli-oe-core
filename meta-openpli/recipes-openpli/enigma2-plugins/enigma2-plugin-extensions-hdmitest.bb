DESCRIPTION = "Plugin for monitoring and testing HdmiCec"
MAINTAINER = "ims"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=534862957bf314f95d85e0c07632f84d"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/ims21/HdmiTest.git;protocol=https;branch=master"

S="${WORKDIR}/git"

inherit setuptools3-openplugins python3-compileall
