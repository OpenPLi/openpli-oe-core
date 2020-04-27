SUMMARY = "Kodi Media Center"

LICENSE = "GPLv2"
#LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"
LIC_FILES_CHKSUM = "file://../../../../LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"

FILESPATH =. "${FILE_DIRNAME}/kodi-18:"

DEPENDS = " \
            lzo \
            jpeg \
            libpng \
            giflib \
          "

SRCREV = "cd02e6c4fe0f6486b967049d697ce086b1afcc56"

# 'patch' doesn't support binary diffs
PATCHTOOL = "git"

PV = "18.0+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=master \
          "

inherit cmake gettext python-dir pythonnative

S = "${WORKDIR}/git/tools/depends/native/TexturePacker"

OECMAKE_CXX_FLAGS_append = " -DTARGET_POSIX -std=gnu++11 -I${WORKDIR}/git/xbmc/linux"

do_configure_prepend() {
    ln -sf ${WORKDIR}/git/xbmc ${WORKDIR}/git/tools/depends/native/TexturePacker/
}

#OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DCMAKE_MODULE_PATH=${WORKDIR}/git/cmake/modules"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/TexturePacker ${D}${bindir}
}

BBCLASSEXTEND = "native"
