DESCRIPTION = "Switching subservices based on XML config"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README.md;md5=6a5d978fb3f988f3d205efc7ad323fb5"

PLUGINNAME = "CustomSubservices"

SRC_URI_append = " file://add-setup-file.patch"

inherit setuptools3

require dima-plugins.inc
