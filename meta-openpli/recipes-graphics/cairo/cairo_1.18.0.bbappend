FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://0001-Reinstate-cairo-directfd.patch"

PACKAGECONFIG:append = " directfb"

PACKAGECONFIG[directfb] = "-Ddirectfb=enabled,-Ddirectfb=disabled,directfb"
