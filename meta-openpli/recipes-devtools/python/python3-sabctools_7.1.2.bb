SUMMARY = "C implementations of functions for use within SABnzbd"
SECTION = "devel/python"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI[md5sum] = "35d6bd261734f53b6658ae3a1d22a93f"
SRC_URI[sha256sum] = "c038055eec5c966a8c9515f2afdaa9aee24970e5df3a23964d95d7e77b98101f"

SRC_URI:append = " file://remove-x64-instructions.patch"

inherit pypi setuptools3

include python3-package-split.inc
