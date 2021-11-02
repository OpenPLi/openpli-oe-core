inherit gitpkgv

PV = "1.1+git${SRCPV}"
PKGV = "1.1+git${GITPKGV}"

SRC_URI = "https://github.com/BjornLee/linux-firmware"
S = "${WORKDIR}/git"

require linux-firmware-local.inc
