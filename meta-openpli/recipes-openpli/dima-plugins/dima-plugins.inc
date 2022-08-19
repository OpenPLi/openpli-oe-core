HOMEPAGE = "https://github.com/Dima73/${PLUGINNAME}"

SRC_URI = "git://github.com/Dima73/${PLUGINNAME}.git;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv distutils-openplugins python3-compileall

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
