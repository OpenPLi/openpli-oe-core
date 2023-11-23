MODULE = "FileCommander"
DESCRIPTION = "File manager based on OpenATV one"

require conf/license/license-gplv2.inc

require openplugins-replace-pli.inc

require openplugins-distutils.inc

SRC_URI:append = " file://use-setuptools-instead-of-distutils.patch \
                   file://set-list-before-update.patch \
"
