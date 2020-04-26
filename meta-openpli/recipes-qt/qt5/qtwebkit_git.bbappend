SRC_URI += " \
        file://Enable-NPAPI-for-Qt-without-X11.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebkit-git:"

INSANE_SKIP_${PN} += "file-rdeps ldflags"

PACKAGE_ARCH := "${MACHINE_ARCH}"

DEPENDS += " bison-native"
