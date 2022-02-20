SUMMARY = "Kodi texture packer"

require kodi_20.inc
inherit autotools-brokensep gettext native

KODIWORKDIR = "${WORKDIR}/git/tools/depends/native/TexturePacker"

DEPENDS = " \
            cmake-native \
            giflib \
            jpeg \
            libpng-native \
            lzo \
            lzo-native \
            libpng \
            libjpeg-turbo \
            libjpeg-turbo-native \
"




S = "${WORKDIR}/git/tools/depends/native/TexturePacker/src"

do_configure_prepend() {
    sed -i '/STATIC_FLAG/d' ${S}/Makefile.am
}


do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/TexturePacker ${D}${bindir}
}

BBCLASSEXTEND = "native"
