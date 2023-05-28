DESCRIPTION = "Display FRITZ!box-Fon calls on screen"
MAINTAINER = "DrMichael"
LICENSE = "CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM = "file://src/LICENSE;md5=ca0fd7efc85d7f5d417c882290c4b9fa"

RDEPENDS_${PN} = "${PYTHON_PN}-twisted-web ${PYTHON_PN}-html ${PYTHON_PN}-json ${PYTHON_PN}-six"

inherit gitpkgv distutils-openplugins gettext python3-compileall

SRC_URI = "\
	git://github.com/DrMichael/FritzCall.git;protocol=https;branch=master \
	file://git/setup.py \
	file://git/setup_translate.py \
	"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

PACKAGE_ARCH = "all"

S="${WORKDIR}/git"
