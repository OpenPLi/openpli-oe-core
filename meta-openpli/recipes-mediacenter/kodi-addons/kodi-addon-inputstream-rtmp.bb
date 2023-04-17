SUMMARY = "kodi inputstream addon for rtmp"

LICENSE = "GPLv2+"
require conf/license/license-gplv2.inc

inherit kodi-addon

DEPENDS += "expat"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "61f7ff342b5c60ff5c9d75f6de843fe4b8cd7aa7"

PV = "20.3.0+gitr${SRCPV}"

KODIADDONBRANCH = "Nexus"

SRC_URI = "git://github.com/xbmc/inputstream.rtmp.git;protocol=https;branch=${KODIADDONBRANCH}"

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.rtmp"
