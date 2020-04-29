# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebkit-git:"

DEPENDS += " libwebp qtdeclarative qtlocation qtsensors"

SRC_URI += " \
    file://0001-Qtwebkit-platform-setting.patch \
    file://0002-Qtwebkit-without-x11.patch \
    ${@bb.utils.contains('MACHINE_FEATURES', 'no-opengl', 'file://0003-Qtwebkit-without-opengl.patch', '', d)} \
"

PACKAGECONFIG = " "

SRCREV = "ab1bd15209abaf7effc51dbc2f272c5681af7223"

INSANE_SKIP_${PN} += "file-rdeps"
