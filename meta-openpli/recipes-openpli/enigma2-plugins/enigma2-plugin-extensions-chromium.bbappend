FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

S = "${WORKDIR}/e2plugin"

SRC_URI_append = " \
           file://0001-move-youtube-to-pluginmenu.patch \
           "
