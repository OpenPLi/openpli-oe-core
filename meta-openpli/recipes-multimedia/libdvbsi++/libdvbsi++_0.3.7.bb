SUMMARY = "C++ parsing library for Service Information (SI) in DVB systems"
AUTHOR = "Andreas Oberritter"
SECTION = "libs/multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"
PR = "r2"

SRCREV = "bf0c2bb7b326b93713629dbf74f63a0a54ffe59d"

SRC_URI = "http://www.saftware.de/${PN}/${P}.tar.bz2 \
	file://fix_section_len_check.patch \
	"
SRC_URI[md5sum] = "7eb50352c06b17c499a6d51e18e99ec5"
SRC_URI[sha256sum] = "f8d412fbe7385e63fa4f02976d3ecae9a87a3936806f20e82ea67f351cae434f"

inherit autotools pkgconfig
