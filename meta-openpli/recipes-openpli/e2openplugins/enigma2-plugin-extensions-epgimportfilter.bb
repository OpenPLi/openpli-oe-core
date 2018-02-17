MODULE = "EPGImportFilter"
DESCRIPTION = "EPGImport Filter"

RDEPENDS_${PN} = "\
	python-difflib \
	python-shell \
	"

inherit gitpkgv
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc
