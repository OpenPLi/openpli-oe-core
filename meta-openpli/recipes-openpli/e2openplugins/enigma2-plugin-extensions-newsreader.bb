MODULE = "NewsReader"
DESCRIPTION = "RSS reader"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

FILES_${PN} += "/etc/feeds.xml"
CONFFILES_${PN} = "/etc/feeds.xml"
