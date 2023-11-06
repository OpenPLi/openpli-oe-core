FILESEXTRAPATHS:prepend := "${THISDIR}/qtwebkit-git:"
# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

DEPENDS += " libwebp qtsensors qtlocation patchelf-native"
RDEPENDS:${PN} += " qtdeclarative qtsensors qtlocation"

SRC_URI:remove = " \
    file://0003-Fix-build-bug-for-armv32-BE.patch \
    file://0005-Let-Bison-generate-the-header-directly-to-fix-build-.patch \
    file://0006-Disable-code-related-to-HTTP-2-when-Qt-is-configured.patch \
    file://0007-Fix-compilation-with-Python-3.9-avoid-passing-encodi.patch \
    file://0009-Riscv-Add-support-for-riscv.patch \
    "
SRC_URI += " \
    file://0001-Qtwebkit-platform-setting.patch \
    file://0002-Qtwebkit-without-x11.patch \
    ${@bb.utils.contains('MACHINE_FEATURES', 'no-opengl', 'file://0003-Qtwebkit-without-opengl.patch', '', d)} \
"
EXTRA_OECMAKE:append:arm = " -DENABLE_JIT=OFF -DUSE_SYSTEM_MALLOC=ON -DENABLE_C_LOOP=ON "

# HACK Close libEGL.so issue fix rpatch
do_install:append() {
    if [ -e ${PKG_CONFIG_SYSROOT_DIR}${libdir}/libEGL.so ]; then
        patchelf --remove-needed ${PKG_CONFIG_SYSROOT_DIR}${libdir}/libEGL.so ${D}${libdir}/libQt5WebKit.so.5.212.0
        patchelf --add-needed libEGL.so ${D}${libdir}/libQt5WebKit.so.5.212.0
    fi
}

SRCREV = "ac8ebc6c3a56064f88f5506e5e3783ab7bee2456"

PACKAGECONFIG = " "

CXXFLAGS:append = " -Wno-deprecated-copy -DBROADCOM_PLATFORM"

INSANE_SKIP:${PN} += "file-rdeps"
