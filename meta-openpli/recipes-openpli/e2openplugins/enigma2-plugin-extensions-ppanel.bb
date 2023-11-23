MODULE = "PPanel"
DESCRIPTION = "PPanel"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

PACKAGES =+ "${PN}-example"
FILES:${PN}-example = "${sysconfdir}/ppanel/PPanel_tutorial.xml"

SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-PPanel.git;branch=python3;protocol=https \
           file://use-setuptools-instead-of-distutils.patch \
"
