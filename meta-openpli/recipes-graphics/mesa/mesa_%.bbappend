do_install_append() {
    # Remove Mesa libraries (EGL, GLESv1, GLESv2, GBM)
    # provided by SOC
    rm -f ${D}/${libdir}/libGLESv1_CM*
    rm -f ${D}/${libdir}/libEGL.so*
    rm -f ${D}/${libdir}/libgbm.so*
    rm -f ${D}/${libdir}/libGLESv1*.so*
    rm -f ${D}/${libdir}/libGLESv2.so*
}

PROVIDES_remove = "virtual/libgles1 virtual/libgles2 virtual/egl virtual/libgbm"

REQUIRED_DISTRO_FEATURES = ""
ANY_OF_DISTRO_FEATURES_class-target = ""

PACKAGECONFIG_class-target = "opengl egl gles gbm dri"

# make sure EGL drivers are build per machine to avoid conflicts
# with hardware / SoC dependent OpenGL drivers
PACKAGE_ARCH = "${MACHINE_ARCH}"
