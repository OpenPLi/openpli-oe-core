MODULE = "NewsReader"
DESCRIPTION = "RSS reader"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

do_install:append() {
	rm -rf ${D}/${datadir}
}

FILES:${PN} += "${sysconfdir}/feeds.xml"
CONFFILES:${PN} = "${sysconfdir}/feeds.xml"
