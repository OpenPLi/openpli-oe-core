MODULE = "MeteoViewer"
DESCRIPTION = "meteo pictures viewer"

RDEPENDS_${PN} = "\
	${PYTHON_PN}-requests \
	"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc
