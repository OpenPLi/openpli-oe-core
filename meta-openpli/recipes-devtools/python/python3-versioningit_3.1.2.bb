SUMMARY = "Versioning It with your Version In Git"
HOMEPAGE = "https://github.com/jwodder/versioningit"
AUTHOR = "John Thorvald Wodder II <versioningit@varonathe.org>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98f24e40391b9cd0b42e3d703bd0c332"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "4db83ed99f56b07d83940bee3445ca46ca120d13b6b304cdb5fb44e5aa4edec0"

PYPI_PACKAGE = "versioningit"

inherit pypi python_hatchling

BBCLASSEXTEND = "native"
