SUMMARY = "decoding library for DTS Coherent Acoustics streams"
SECTION = "libs/multimedia"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit autotools gittag

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://code.videolan.org/videolan/libdca.git;protocol=https;branch=master"
SRC_URI := " \
    ${SRC_ORIGIN} \
    file://fix-libdts-link-path.patch \
    "

S = "${WORKDIR}/git"
