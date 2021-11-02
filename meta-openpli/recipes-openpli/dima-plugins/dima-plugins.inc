HOMEPAGE = "https://github.com/Dima73/${PLUGINNAME}"

SRC_URI = "https://github.com/Dima73/${PLUGINNAME}"

S = "${WORKDIR}/git"

inherit gitpkgv distutils-openplugins

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
