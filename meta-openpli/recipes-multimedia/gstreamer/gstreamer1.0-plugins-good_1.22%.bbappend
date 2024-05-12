FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS:append = " libsoup-2.4"
RDEPENDS:${PN}-soup += "libsoup-2.4"

SRC_URI:append = " file://001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch"

PACKAGECONFIG = "${GSTREAMER_ORC} bz2 cairo flac gdk-pixbuf gudev jpeg lame libpng \
                 mpg123 soup2 speex taglib v4l2 vpx wavpack \
"
