SUMMARY = "Kodi texture packer"

require kodi.inc
inherit cmake gettext python3-dir python3native

S = "${WORKDIR}/git/tools/depends/native/TexturePacker"

DEPENDS = " \
    giflib \
    jpeg \
    libpng \
    lzo \
    libjpeg-turbo \
    libjpeg-turbo-native \
"

OECMAKE_CXX_FLAGS:append = " -DTARGET_POSIX -std=gnu++17 -I${WORKDIR}/git/xbmc/linux"

do_configure:prepend() {
    ln -sf ${WORKDIR}/git/xbmc ${WORKDIR}/git/tools/depends/native/TexturePacker/
}

EXTRA_OECMAKE = "-DCMAKE_MODULE_PATH=${WORKDIR}/git/cmake/modules"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/TexturePacker ${D}${bindir}
}

BBCLASSEXTEND = "native"
