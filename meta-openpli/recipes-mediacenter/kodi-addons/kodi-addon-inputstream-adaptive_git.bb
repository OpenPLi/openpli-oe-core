SUMMARY = "kodi inputstream addon for several manifest types"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/main.cpp;beginline=1;endline=17;md5=5eac1e215251c8f88d799f80ed45d5c0"

inherit kodi-addon

DEPENDS += "expat"
RDEPENDS_${PN} += "ldd"
RRECOMMENDS_${PN} = "kernel-module-ext2"

SRCREV = "${AUTOREV}"

KODIADDONBRANCH = "Leia"

PV = "2.4.7+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/inputstream.adaptive.git;protocol=https;branch=${KODIADDONBRANCH} \
          "

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.adaptive"

# Fix QA: found library in wrong location:
# /usr/share/kodi/addons/inputstream.adaptive/libssd_wv.so [libdir]
INSANE_SKIP_${PN} = "libdir"

