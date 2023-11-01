SUMMARY = "C implementations of functions for use within SABnzbd"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI[md5sum] = "6277dd0d4f6319d22ca34b63fd8a880e"
SRC_URI[sha256sum] = "86b4691158669e6e00052a8dfc5dcc9650a0a090a0d4f74cfa856b411fae65b9"

SRC_URI:append = " file://remove-x64-instructions.patch"

inherit pypi setuptools3

include python3-package-split.inc
