SUMMARY  = "Standalone ISO 3166 Country definitions"
HOMEPAGE = "https://github.com/deactivated/python-iso3166"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5e2f4edc7e7408a82e4a1d05f229b695"

SRC_URI[sha256sum] = "b1e58dbcf50fbb2c9c418ec7a6057f0cdb30b8f822ac852f72e71ba769dae8c5"

inherit pypi setuptools

PACKAGES = "${PN}"

BBCLASSEXTEND = "native nativesdk"
