SUMMARY = "kodi inputstream addon for ffmpeg"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit kodi-addon
PV = "1.21.6"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/xbmc/inputstream.ffmpegdirect.git;branch=Matrix;protocol=https"

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.ffmpegdirect"
