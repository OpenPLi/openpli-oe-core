MODULE = "MeteoViewer"
DESCRIPTION = "meteo pictures viewer"

RDEPENDS:${PN} = "\
	python3-requests \
	"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc
