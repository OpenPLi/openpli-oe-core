MODULE = "MeteoViewer"
DESCRIPTION = "meteo pictures viewer"

RDEPENDS_${PN} = "\
	${PYTHON_VER}-requests \
	"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc
