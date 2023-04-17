SUMMARY = "Kodi inputstream addon for several manifest types"
HOMEPAGE = "https://github.com/peak3d/inputstream.adaptive"
BUGTRACKER = "https://github.com/peak3d/inputstream.adaptive/issues"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/main.cpp;beginline=1;endline=17;md5=7906619e2feca59ac3f5088ac7bc4100"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit kodi-addon

DEPENDS += "expat bento4"
RDEPENDS_${PN} += "ldd"
RRECOMMENDS_${PN} = "kernel-module-ext2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

KODIADDONBRANCH = "Nexus"

PV = "20.3.4+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/inputstream.adaptive.git;protocol=https;branch=${KODIADDONBRANCH} \
        file://define-INPUTSTREAM_MAX_STREAM_COUNT-ifndef.patch"

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.adaptive"

INSANE_SKIP_${PN} = "libdir dev-so"
