FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "1.24.5"

SRC_URI[sha256sum] = "3029bfd7265314d609dc8eab503675a344ea46e8274fd73ab34566c8442dc847"

SRC_URI:append = "file://001-rtmp-hls-tsdemux-fix.patch \
                  file://003-rtmp-fix-seeking-and-potential-segfault.patch \
"

PACKAGECONFIG = "${GSTREAMER_ORC} bz2 closedcaption curl dash dtls faac faad hls openssl opusparse \
                 rsvg rtmp sbc smoothstreaming sndfile ttml uvch264 webp \
"

EXTRA_OEMESON:remove = "-Dkate=disabled"
