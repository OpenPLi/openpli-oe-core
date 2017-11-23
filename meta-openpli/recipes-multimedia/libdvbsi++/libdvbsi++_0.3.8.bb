SUMMARY = "C++ parsing library for Service Information (SI) in DVB systems"
AUTHOR = "Andreas Oberritter"
SECTION = "libs/multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI = "git://git.opendreambox.org/git/obi/libdvbsi++.git \
	file://fix_section_len_check.patch \
	file://0001-ac3_descriptor-check-if-header-is-larger-than-descri.patch \
	"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
