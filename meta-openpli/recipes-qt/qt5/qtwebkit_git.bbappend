SRC_URI += " \
        file://Enable-NPAPI-for-Qt-without-X11.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebkit-git:"

CXXFLAGS += "-fpermissive"

INSANE_SKIP_${PN} += "file-rdeps"

PACKAGE_ARCH := "${MACHINE_ARCH}"
