MODULE = "SystemTools"
DESCRIPTION = "System Tools for enigma2 stb"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-SystemTools.git;branch=python3;protocol=https file://use-setuptools-instead-of-distutils.patch"
