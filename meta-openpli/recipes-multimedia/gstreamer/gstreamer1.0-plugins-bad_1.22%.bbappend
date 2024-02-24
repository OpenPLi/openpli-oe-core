FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://001-rtmp-hls-tsdemux-fix.patch \
                  file://003-rtmp-fix-seeking-and-potential-segfault.patch \
"

PACKAGECONFIG = "${GSTREAMER_ORC} bz2 closedcaption curl dash dtls faac faad hls openssl opusparse \
                 rsvg rtmp sbc smoothstreaming sndfile ttml uvch264 webp \
"
