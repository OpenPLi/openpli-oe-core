FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "1.24.5"

SRC_URI[sha256sum] = "badcfc5292b035bde99a77327d468b2f0b116b40420bc9f25fb8e3970824ba75"

DEPENDS:append = " libsoup-2.4"
RDEPENDS:${PN}-soup += "libsoup-2.4"

SRC_URI:append = " file://001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch"
SRC_URI:remove = "file://0001-qt-include-ext-qt-gstqtgl.h-instead-of-gst-gl-gstglf.patch file://0001-v4l2-Define-ioctl_req_t-for-posix-linux-case.patch"

PACKAGECONFIG = "${GSTREAMER_ORC} amrnb amrwb bz2 cairo flac gdk-pixbuf gudev jpeg lame libpng \
                 mpg123 soup2 speex taglib v4l2 vpx wavpack \
"

PACKAGECONFIG[amrnb] = "-Damrnb=enabled,-Damrnb=disabled,opencore-amr"
PACKAGECONFIG[amrwb] = "-Damrwbdec=enabled,-Damrwbdec=disabled,opencore-amr"
