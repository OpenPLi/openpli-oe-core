MODULE = "TelekomSport"
DESCRIPTION = "Telekom Sport Plugin"

inherit gittag
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc
