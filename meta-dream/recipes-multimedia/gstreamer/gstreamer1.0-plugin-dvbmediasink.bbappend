FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://dmm.patch \
	file://0001-meta-dream-fix-framerate-and-video-size.patch"
