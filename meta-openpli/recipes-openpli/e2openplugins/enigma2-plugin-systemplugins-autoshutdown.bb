MODULE = "AutoShutDown"
DESCRIPTION = "automated power off for STB"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit gitpkgv

PV = "0.6+git${SRCPV}"
PKGV = "0.6+git${GITPKGV}"
PR = "r0"

require conf/license/license-gplv2.inc
require openplugins.inc

inherit autotools gettext

FILES_${PN} = "/"
