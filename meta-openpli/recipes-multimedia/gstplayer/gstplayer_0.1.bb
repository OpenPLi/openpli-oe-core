DESCRIPTION = "gstplayer by samsamsam"
SECTION = "multimedia"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

inherit pkgconfig

SRC_URI = "git://gitlab.com/e2i/gstplayer.git;protocol=http"
SRC_URI =+ "file://0001-set-iptv-download-timeout-0-to-disable-ifdsrc.patch \
            file://0004-rename-stored-sink-to-dvbSink-for-clarity.patch \
            file://0009-try-to-get-PTS-from-video-sink-first.patch \
            file://0011-increase-eos-fix-timeout-to-10s.patch"

S = "${WORKDIR}/git"

do_compile() {
    cd ${S}/gst-1.0
    ${CC} *.c ../common/*.c -I../common/ `pkg-config --cflags --libs gstreamer-1.0 gstreamer-pbutils-1.0` -o gstplayer_gst-1.0 ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/gst-1.0/gstplayer_gst-1.0 ${D}${bindir}/gstplayer
}

pkg_postinst_ontarget_${PN}() {
    ln -sf gstplayer ${bindir}/gstplayer_gst-1.0
}

pkg_prerm_${PN}() {
    rm -f ${bindir}/gstplayer_gst-1.0
}
