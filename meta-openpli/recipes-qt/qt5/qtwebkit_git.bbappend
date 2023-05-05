# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebkit-git:"

DEPENDS += " libwebp qtsensors qtlocation patchelf-native"
RDEPENDS_${PN} += " qtdeclarative qtsensors qtlocation"

SRC_URI_remove = " \
    file://0003-Fix-build-bug-for-armv32-BE.patch \
    "

SRC_URI += " \
    file://0001-Qtwebkit-platform-setting.patch \
    file://0002-Qtwebkit-without-x11.patch \
    ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://0003-workaround-segment-error.patch', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'no-opengl', 'file://0003-Qtwebkit-without-opengl.patch', '', d)} \
"

PACKAGECONFIG = " "

# HACK Close libEGL.so issue fix rpatch
do_install_append() {
    if [ -e ${PKG_CONFIG_SYSROOT_DIR}${libdir}/libEGL.so ]; then
        patchelf --remove-needed ${PKG_CONFIG_SYSROOT_DIR}${libdir}/libEGL.so ${D}${libdir}/libQt5WebKit.so.5.212.0
        patchelf --add-needed libEGL.so ${D}${libdir}/libQt5WebKit.so.5.212.0
    fi
}

QT_MODULE_BRANCH = "5.212"

SRCREV = "ac8ebc6c3a56064f88f5506e5e3783ab7bee2456"

INSANE_SKIP_${PN} += "file-rdeps"
