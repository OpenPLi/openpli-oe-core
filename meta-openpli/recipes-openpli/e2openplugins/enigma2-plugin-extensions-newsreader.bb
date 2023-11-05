MODULE = "NewsReader"
DESCRIPTION = "RSS reader"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

FILES:${PN} += "${sysconfdir}/feeds.xml"
CONFFILES:${PN} = "${sysconfdir}/feeds.xml"
