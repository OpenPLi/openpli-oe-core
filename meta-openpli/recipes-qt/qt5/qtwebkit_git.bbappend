SRC_URI += " \
        file://Enable-NPAPI-for-Qt-without-X11.patch \
        file://0001-Fix-compilation-with-ICU-59.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebkit-git:"