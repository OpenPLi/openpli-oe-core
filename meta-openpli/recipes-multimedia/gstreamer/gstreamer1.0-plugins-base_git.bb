include gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                   "

S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV = "112cc33d6a3a89fb290dfe81a9d2116502a22e85"
SRCREV_common = "29046b89d80bbca22eb222c18820fb40a4ac5bde"
SRCREV_FORMAT = "base"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-base;branch=master \
	git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
	file://0001-Makefile.am-don-t-hardcode-libtool-name-when-running.patch \
	file://0002-Makefile.am-prefix-calls-to-pkg-config-with-PKG_CONF.patch \
	file://0003-riff-media-added-fourcc-to-all-ffmpeg-mpeg4-video-caps.patch \
	file://0004-rtsp-drop-incorrect-reference-to-gstreamer-sdp-in-Ma.patch \
"

inherit gitpkgv
PV = "1.12.00+git${SRCPV}"
PKGV = "1.12.00+git${GITPKGV}"


do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

