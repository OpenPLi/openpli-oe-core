SUMMARY = "Fuzzy string matching in python"
HOMEPAGE = "https://github.com/seatgeek/thefuzz"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f370a4436f8e1656d00272929f73ec3b"

inherit pypi setuptools3

SRC_URI[md5sum] = "0b7ec0d80b46c90d113df62892d78395"
SRC_URI[sha256sum] = "7138039a7ecf540da323792d8592ef9902b1d79eb78c147d4f20664de79f3680"

include python3-package-split.inc
