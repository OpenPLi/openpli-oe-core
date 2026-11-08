inherit gitpkgv

PV = "1.1+git"
PKGV = "1.1+git${GITPKGV}"

SRC_URI = "git://github.com/ZGEMMA-ANDROID/linux-firmware.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

require linux-firmware-local.inc
