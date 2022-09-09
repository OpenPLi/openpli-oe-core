SUMMARY = "kodi inputstream addon for rtmp"

LICENSE = "GPLv2+"
require conf/license/license-gplv2.inc

inherit kodi-addon

DEPENDS += "expat"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "c6e2bbe16ce284cab2b6c4bc98d551a99db8a32b"

PV = "19.4.0+gitr${SRCPV}"

KODIADDONBRANCH = "Matrix"

SRC_URI = "git://github.com/xbmc/inputstream.rtmp.git;protocol=https;branch=${KODIADDONBRANCH}"

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.rtmp"
