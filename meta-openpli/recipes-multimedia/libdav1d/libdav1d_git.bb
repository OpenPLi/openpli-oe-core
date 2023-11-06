SUMMARY = "dav1d is an AV1 decoder"
HOMEPAGE = "https://code.videolan.org/videolan/dav1d"
SECTION = "libs/multimedia"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c8055cfe7548dfdaa3a6dc45d8793669"

inherit gittag

SRCREV ?= "f2a8fc1339d6cd72bb182ebb688f041688e39bee"
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://code.videolan.org/videolan/dav1d.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit meson
