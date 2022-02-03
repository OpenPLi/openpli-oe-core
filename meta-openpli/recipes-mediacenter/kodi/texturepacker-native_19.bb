SUMMARY = "Kodi texture packer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

DEPENDS = " \
    cmake-native \
    giflib-native \
    libpng-native \
    lzo-native \
    libpng \
    libjpeg-turbo \
    libjpeg-turbo-native \
"

SRCREV = "${AUTOREV}"

PV = "19.3+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Matrix"


S = "${WORKDIR}/git/tools/depends/native/TexturePacker/src"


EXTRA_OECMAKE = "-DCMAKE_MODULE_PATH=${WORKDIR}/git/cmake/modules"

inherit autotools-brokensep gettext native

S = "${WORKDIR}/git/tools/depends/native/TexturePacker/src"

do_configure_prepend() {
    sed -i '/STATIC_FLAG/d' ${S}/Makefile.am
}

BBCLASSEXTEND = "native"
