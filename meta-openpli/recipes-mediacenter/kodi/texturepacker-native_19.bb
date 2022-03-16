SUMMARY = "Kodi texture packer"

require kodi_19.inc
inherit cmake pkgconfig autotools-brokensep gettext native

DEPENDS = "\
            giflib-native \
            jpeg \
            libpng-native \
            lzo-native \
            libpng \
            lzo \
            libjpeg-turbo \
            libjpeg-turbo-native \
          "


S = "${WORKDIR}/git/tools/depends/native/TexturePacker/src"

do_configure:prepend() {
    sed -i '/STATIC_FLAG/d' ${S}/Makefile.am
}
