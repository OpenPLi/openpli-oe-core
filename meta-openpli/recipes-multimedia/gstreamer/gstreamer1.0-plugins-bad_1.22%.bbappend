FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "1.24.6"

SRC_URI[sha256sum] = "8f4d965513e7827c1f547c1e749e4ef2812b371974fe64857fe5029a19c5b64c"

SRC_URI:append = "file://001-rtmp-hls-tsdemux-fix.patch \
                  file://003-rtmp-fix-seeking-and-potential-segfault.patch \
"

PACKAGECONFIG = "${GSTREAMER_ORC} bz2 closedcaption curl dash dtls faac faad hls openssl opusparse \
                 rsvg rtmp sbc smoothstreaming sndfile ttml uvch264 webp \
"

EXTRA_OEMESON:remove = "-Dkate=disabled"
