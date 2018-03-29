require gstreamer1.0-plugins.inc


LICENSE = "GPLv2+ & LGPLv2+"

DEPENDS += "util-linux iso-codes zlib"

inherit gettext

PACKAGES_DYNAMIC =+ "^libgst.*"

# opengl packageconfig factored out to make it easy for distros
# and BSP layers to pick either (desktop) opengl, gles2, or no GL
PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"

PACKAGECONFIG ??= " \
    ${PACKAGECONFIG_GL} \
    ${GSTREAMER_ORC} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa x11', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland egl', '', d)} \
    orc gio-unix-2.0 ivorbis ogg theora vorbis cdparanoia pango opus \
    "

X11DEPENDS = "virtual/libx11 libsm libxrender libxv"
X11ENABLEOPTS = "--enable-x --enable-xvideo --enable-xshm"
X11DISABLEOPTS = "--disable-x --disable-xvideo --disable-xshm"

PACKAGECONFIG[alsa]         = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[cdparanoia]   = "--enable-cdparanoia,--disable-cdparanoia,cdparanoia"
PACKAGECONFIG[gio-unix-2.0] = "--enable-gio_unix_2_0,--disable-gio_unix_2_0,glib-2.0"
PACKAGECONFIG[ivorbis]      = "--enable-ivorbis,--disable-ivorbis,tremor"
PACKAGECONFIG[ogg]          = "--enable-ogg,--disable-ogg,libogg"
PACKAGECONFIG[opus]         = "--enable-opus,--disable-opus,libopus"
PACKAGECONFIG[pango]        = "--enable-pango,--disable-pango,pango"
PACKAGECONFIG[theora]       = "--enable-theora,--disable-theora,libtheora"
PACKAGECONFIG[visual]       = "--enable-libvisual,--disable-libvisual,libvisual"
PACKAGECONFIG[vorbis]       = "--enable-vorbis,--disable-vorbis,libvorbis"
PACKAGECONFIG[x11]          = "${X11ENABLEOPTS},${X11DISABLEOPTS},${X11DEPENDS}"
PACKAGECONFIG[gles2]        = "--enable-gles2,--disable-gles2,virtual/libgles2"
PACKAGECONFIG[egl]          = "--enable-egl,--disable-egl,virtual/egl"
PACKAGECONFIG[opengl]       = "--enable-opengl,--disable-opengl,virtual/libgl libglu"
PACKAGECONFIG[wayland]      = "--enable-wayland,--disable-wayland,wayland-native wayland wayland-protocols"

EXTRA_OECONF += " \
    --enable-zlib \
    "

CACHED_CONFIGUREVARS_append_x86 = " ac_cv_header_emmintrin_h=no ac_cv_header_xmmintrin_h=no "

FILES_${MLPREFIX}libgsttag-1.0 += "${datadir}/gst-plugins-base/1.0/license-translations.dict"

do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/gst-libs/gst/tag/.libs:${B}/gst-libs/gst/video/.libs:${B}/gst-libs/gst/audio/.libs:${B}/gst-libs/gst/rtp/.libs"
}
