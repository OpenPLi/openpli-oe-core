DESCRIPTION = "QuadPiP plugin for VU+ UHD receivers"

require conf/license/openpli-gplv2.inc

inherit gitpkgv
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

S = "${WORKDIR}/git"
SRC_URI = "https://github.com/OpenPLi/enigma2-plugin-systemplugins-quadpip;protocol=http"

inherit distutils-openplugins
