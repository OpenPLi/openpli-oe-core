SUMMARY = "C implementations of functions for use within SABnzbd"
SECTION = "devel/python"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI[md5sum] = "0acda41a34f0743d5e2c98abc47ec757"
SRC_URI[sha256sum] = "3d87db991f704f74874fea05c90176175de0ec581dbd8fe5f69d03eb973ff915"

SRC_URI:append = " file://remove-x64-instructions.patch"

inherit pypi setuptools3

include python3-package-split.inc
