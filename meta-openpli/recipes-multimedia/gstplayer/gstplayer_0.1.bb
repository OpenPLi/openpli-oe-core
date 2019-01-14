DESCRIPTION = "gstplayer by samsamsam"
SECTION = "multimedia"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

inherit pkgconfig

SRC_URI = "git://gitlab.com/e2i/gstplayer.git;protocol=http"
SRC_URI =+ "file://0001-set-iptv-download-timeout-0-to-disable-ifdsrc.patch \
            file://0004-rename-stored-sink-to-dvbSink-for-clarity.patch \
            file://0009-try-to-get-PTS-from-video-sink-first.patch \
            file://0011-increase-eos-fix-timeout-to-10s.patch"

SRC_URI[md5sum] = "06ff9a6164b6e793ad640e5556d6e869"
SRC_URI[sha256sum] = "c7dadc3ba3159c1ac191e0f391e5b1e36ed721de88343ac62dfe3df115304afe"

S = "${WORKDIR}/git/"

do_compile() {
    cd ${S}/gst-1.0
    ${CC} *.c ../common/*.c -I../common/ `pkg-config --cflags --libs gstreamer-1.0 gstreamer-pbutils-1.0` -o gstplayer_gst-1.0 ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/gst-1.0/gstplayer_gst-1.0 ${D}${bindir}/gstplayer
}

pkg_postinst_${PN}() {
    ln -sf gstplayer ${bindir}/gstplayer_gst-1.0
}

pkg_prerm_${PN}() {
    rm -f ${bindir}/gstplayer_gst-1.0
}
