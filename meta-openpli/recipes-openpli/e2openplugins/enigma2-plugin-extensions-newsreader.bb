MODULE = "NewsReader"
DESCRIPTION = "RSS reader"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

FILES_${PN} += "${sysconfdir}/feeds.xml"
CONFFILES_${PN} = "${sysconfdir}/feeds.xml"
