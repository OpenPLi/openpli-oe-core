DESCRIPTION = "Mali OpenGL"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PR = "${SRCDATE}"
DEPENDS = "libdrm mesa patchelf-native"
VER ?= "${@bb.utils.contains('TARGET_ARCH', 'aarch64', '64' , '', d)}"

PROVIDES = "virtual/libgles2 virtual/libgles1 virtual/egl virtual/libgbm"
RPROVIDES:${PN} = "libMali.so libmali.so libEGL.so.1.4 libEGL.so.1 libEGL.so libGLESv1_CM.so.1.1 libGLESv1_CM.so.1 libGLESv1_CM.so libGLESv2.so.2.0 libGLESv2.so.2 libGLESv2.so libgbm.so libgbm.so.1"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/zgemma-mali${VER}-${HICHIPSET}-${SRCDATE}.zip"

S = "${WORKDIR}"

# The driver is a set of binary libraries to install
# there's nothing to configure or compile
do_configure[noexec] = "1"

# Generate a mali rules script
do_compile:append () {
	cat > 50-mali.rules << EOF
KERNEL=="mali0", MODE="0660", GROUP="video"
EOF
}

do_install:append() {
    install -d ${D}${libdir}
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${S}/50-mali.rules ${D}${sysconfdir}/udev/rules.d/50-mali.rules
    install -m 0755 ${S}/libMali.so ${D}${libdir}
    (cd ${D}/${libdir} && ln -sf libMali.so libmali.so)
    (cd ${D}/${libdir} && ln -sf libMali.so libEGL.so.1.4 \
    && ln -sf libEGL.so.1.4 libEGL.so.1 \
    && ln -sf libEGL.so.1 libEGL.so)
    (cd ${D}/${libdir} && ln -sf libMali.so libGLESv1_CM.so.1.1 \
    && ln -sf libGLESv1_CM.so.1.1 libGLESv1_CM.so.1 \
    && ln -sf libGLESv1_CM.so.1 libGLESv1_CM.so)
    (cd ${D}/${libdir} && ln -sf libMali.so libGLESv2.so.2.0 \
    && ln -sf libGLESv2.so.2.0 libGLESv2.so.2 \
    && ln -sf libGLESv2.so.2 libGLESv2.so)
    (cd ${D}/${libdir} && ln -sf libMali.so libgbm.so.1 \
    && ln -sf libgbm.so.1 libgbm.so)
}

FILES:${PN} = "${libdir} /etc/udev/rules.d/50-mali.rules"
FILES:${PN}-dev = "${incdir}"

INSANE_SKIP:${PN} += "already-stripped dev-so ldflags 32bit-time"

# The driver is missing EGL/GLES headers and pkgconfig files. Handle
# the conflicts as mesa and the driver are both providing the same shared libraries.
RREPLACES:${PN} = "libegl libegl1 libgles1 libglesv1-cm1 libgles2 libglesv2-2 libgbm"
RPROVIDES:${PN} = "libegl libegl1 libgles1 libglesv1-cm1 libgles2 libglesv2-2 libgbm"
RCONFLICTS:${PN} = "libegl libegl1 libgles1 libglesv1-cm1 libgles2 libglesv2-2 libgbm"
