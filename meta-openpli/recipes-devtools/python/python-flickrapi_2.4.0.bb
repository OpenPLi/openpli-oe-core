SUMMARY = "The official Python interface to the Flickr API"
HOMEPAGE = "http://stuvel.eu/flickrapi"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "PSF"
PR = "r1"
# NOTE: requires python-misc for webbrowser and subprocess as missing dependency of the webbrowser
RDEPENDS_${PN} = "\
  python-core \
  python-logging \
  python-misc \
  python-netclient \
  python-subprocess \
  python-threading \
  python-xml \
"

SRC_URI = "https://pypi.python.org/packages/source/f/flickrapi/flickrapi-${PV}.tar.gz"

SRC_URI[md5sum] = "94e9b9ac81b1dae1b226e22ac6a4375b"
SRC_URI[sha256sum] = "ffb27b0e4f6d3ae8fc7d20e4696d2de2f95093ecf665a93d7f1d5a3383f8e20d"

S = "${WORKDIR}/flickrapi-${PV}"

inherit distutils

include python-package-split.inc
