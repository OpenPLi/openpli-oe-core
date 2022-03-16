SUMMARY = "kodi inputstream addon for several manifest types"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/main.cpp;beginline=1;endline=17;md5=5eac1e215251c8f88d799f80ed45d5c0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit kodi-addon

DEPENDS += "expat bento4"
RDEPENDS_${PN} += "ldd"
RRECOMMENDS_${PN} = "kernel-module-ext2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

KODIADDONBRANCH = "Matrix"

PV = "19.4.0+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/inputstream.adaptive.git;protocol=https;branch=${KODIADDONBRANCH} \
        file://define-INPUTSTREAM_MAX_STREAM_COUNT-ifndef.patch"

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.adaptive"

INSANE_SKIP_${PN} = "libdir dev-so"
