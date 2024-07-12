SUMMARY = "C implementations of functions for use within SABnzbd"
SECTION = "devel/python"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI[md5sum] = "eb0ffdd32354b23454f8df74fd50de78"
SRC_URI[sha256sum] = "70fdc60a9da61a2bed8f203beafaad3bb989c998c778d92f04b976917c4e4f9c"

SRC_URI:append = " file://remove-x64-instructions.patch"

inherit pypi setuptools3

include python3-package-split.inc
