inherit gitpkgv

PV = "1.1+git${SRCPV}"
PKGV = "1.1+git${GITPKGV}"

SRC_URI = "git://github.com/BjornLee/linux-firmware.git;protocol=https"
S = "${WORKDIR}/git"

require linux-firmware-local.inc
