SUMMARY = "C implementations of functions for use within SABnzbd"
SECTION = "devel/python"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI[md5sum] = "b26e3c8d48797e6912546dc2519ec064"
SRC_URI[sha256sum] = "6440bcd77fc9a463c414bfa75ca1405c57d4aeb84408222a38d7b6ecbc12d348"

SRC_URI:append = " file://remove-x64-instructions.patch"

inherit pypi setuptools3

include python3-package-split.inc
