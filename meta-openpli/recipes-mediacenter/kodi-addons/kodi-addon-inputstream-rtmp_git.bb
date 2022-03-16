SUMMARY = "kodi inputstream addon for rtmp"

LICENSE = "GPLv2+"
require conf/license/license-gplv2.inc

inherit kodi-addon

DEPENDS += "expat"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

PV = "19.4.0+gitr${SRCPV}"

KODIADDONBRANCH = "Matrix"

SRC_URI = "git://github.com/xbmc/inputstream.rtmp.git;protocol=https;branch=${KODIADDONBRANCH}"

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.rtmp"
