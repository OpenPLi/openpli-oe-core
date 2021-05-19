SUMMARY = "Portable network interface information for Python"
DESCRIPTION = "Portable network interface information for Python"
HOMEPAGE = "https://github.com/al45tair/netifaces"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=d68ff546b11a03cda3aec0229188b6a2"

inherit pypi setuptools

SRC_URI[md5sum] = "de92cc322b4f56047c073f802ad77860"
SRC_URI[sha256sum] = "2dee9ffdd16292878336a58d04a20f0ffe95555465fee7c9bd23b3490ef2abf3"

include python-package-split.inc
