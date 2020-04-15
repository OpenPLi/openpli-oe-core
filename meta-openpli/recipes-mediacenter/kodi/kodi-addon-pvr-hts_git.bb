SUMMARY = "Kodi Media Center PVR plugins"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/client.cpp;beginline=1;endline=19;md5=101ceb1255392ba347350e6c3e543f3a"

inherit kodi-addon

SRCREV_pvrhts = "f4ea60f944d0cfb8b73c0f9dc2181e54d3c367bf"

SRCREV_FORMAT = "pvrhts"

PV = "4.4.3+gitr${SRCPV}"
SRC_URI = "git://github.com/kodi-pvr/pvr.hts.git;branch=master;destsuffix=pvr.hts;name=pvrhts;protocol=https \
          "

S = "${WORKDIR}/pvr.hts"

KODIADDONNAME = "pvr.hts"
