SRC_URI += " \
        file://Enable-NPAPI-for-Qt-without-X11.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebkit-git:"

PACKAGE_ARCH := "${MACHINE_ARCH}"
