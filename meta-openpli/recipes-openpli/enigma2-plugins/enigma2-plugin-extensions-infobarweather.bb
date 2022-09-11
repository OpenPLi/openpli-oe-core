DESCRIPTION = "Infobar Weather plugin"
MAINTAINER = "scriptmelvin"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"

inherit gitpkgv distutils-openplugins gettext python3-compileall

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/scriptmelvin/enigma2-plugin-extensions-infobarweather.git;protocol=https;branch=main"

S="${WORKDIR}/git"
