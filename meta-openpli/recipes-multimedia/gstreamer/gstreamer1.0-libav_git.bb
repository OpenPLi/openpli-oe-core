SUMMARY = "Libav-based GStreamer 1.x plugin"
HOMEPAGE = "http://gstreamer.freedesktop.org/"
SECTION = "multimedia"
LICENSE = "GPLv2+ & LGPLv2+ & ( (GPLv2+ & LGPLv2.1+) | (GPLv3+ & LGPLv3+) )"
LICENSE_FLAGS = "commercial"

require gstreamer1.0-common.inc

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base ffmpeg"

SRC_URI = "git://gitlab.freedesktop.org/gstreamer/gst-libav.git;protocol=https;branch=master;name=gst_libav"

inherit pkgconfig

CFLAGS += "-Wno-implicit-function-declaration -Wno-stringop-overflow"

CFLAGS_remove_sh4 = "-Wno-stringop-overflow"
CFLAGS_append_sh4 = " -std=gnu99"

FILES_${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-1.0/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"
