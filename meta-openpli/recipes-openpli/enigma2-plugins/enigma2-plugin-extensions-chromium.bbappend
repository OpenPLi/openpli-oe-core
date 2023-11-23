FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/e2plugin"

SRC_URI:append = " \
           file://0001-move-youtube-to-pluginmenu.patch \
           "
