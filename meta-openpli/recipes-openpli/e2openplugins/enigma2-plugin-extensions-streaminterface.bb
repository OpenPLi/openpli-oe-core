MODULE = "StreamInterface"
DESCRIPTION = "Stream webinterface on port 40080"

RDEPENDS_${PN} = "${PYTHON_PN}-twisted-web"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc
