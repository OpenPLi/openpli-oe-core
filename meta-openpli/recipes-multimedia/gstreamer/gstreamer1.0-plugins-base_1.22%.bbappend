FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RDEPENDS:libgstgl-1.0 = "libGLESv2.so"

PV = "1.24.5"

SRC_URI[sha256sum] = "0e33ec9b59eef5ef3a6a53bbd55c44340e681d0000910caca12541a73db38a7d"

SRC_URI:append = " file://001-riff-media-added-fourcc-to-all-ffmpeg-mpeg4-video-caps.patch \
                   file://002-subparse-avoid-false-negatives-dealing-with-UTF-8.patch \
                   file://define-surfaceless-mesa-macro.patch \
"

PACKAGECONFIG = "${GSTREAMER_ORC} alsa jpeg ogg opus pango png theora vorbis"
