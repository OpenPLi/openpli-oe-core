FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PROVIDES =+ " librtmp librtmp1"

SRC_URI = " \
	git://git.ffmpeg.org/rtmpdump;protocol=git;branch=master \
	file://ksv.patch;striplevel=0 \
	file://iptvplayer_release.patch \
	"
