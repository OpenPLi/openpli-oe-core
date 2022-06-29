FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

S = "${WORKDIR}/e2plugin"

SRC_URI_append = " \
           file://0001-move-youtube-to-pluginmenu.patch \
           ${@bb.utils.contains("MACHINE", "^(gbquad4k|gbue4k)$", "file://port-to-python3.patch", "file://port-to-python3-gigablue.patch", d)} \
           "
