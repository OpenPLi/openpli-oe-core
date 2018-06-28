SUMMARY  = "Portable network interface information."
DESCRIPTION = "An easy way to get the address(es) of the machine’s network interfaces from Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=bc7d03606b982d3cd368dd1bf614c5ae"

inherit pypi setuptools

SRC_URI[md5sum] = "5b4d1f1310ed279e6df27ef3a9b71519"
SRC_URI[sha256sum] = "59d8ad52dd3116fcb6635e175751b250dc783fb011adba539558bd764e5d628b"

include python-package-split.inc
