SUMMARY = "Kodi texture packer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

DEPENDS = "\
            giflib \
            jpeg \
            libpng \
            lzo \
          "

SRCREV = "0655c2c71821567e4c21c1c5a508a39ab72f0ef1"

PV = "18.9+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=Leia"

inherit cmake gettext python-dir pythonnative

S = "${WORKDIR}/git/tools/depends/native/TexturePacker"

OECMAKE_CXX_FLAGS_append = " -DTARGET_POSIX -std=gnu++11 -I${WORKDIR}/git/xbmc/linux"

do_configure_prepend() {
    ln -sf ${WORKDIR}/git/xbmc ${WORKDIR}/git/tools/depends/native/TexturePacker/
}

EXTRA_OECMAKE = "-DCMAKE_MODULE_PATH=${WORKDIR}/git/cmake/modules"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/TexturePacker ${D}${bindir}
}

BBCLASSEXTEND = "native"
