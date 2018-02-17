MODULE = "WakeOnLan"
DESCRIPTION = "Send a WOL packet to devices"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc

inherit gitpkgv
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"
