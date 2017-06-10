require gstreamer1.0-libav.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://ext/libav/gstav.h;beginline=1;endline=18;md5=a752c35267d8276fd9ca3db6994fca9c \
"


# To build using the system libav/ffmpeg, append "libav" to PACKAGECONFIG
# and remove the ffmpeg sources from SRC_URI below. However, first note the
# warnings in gstreamer1.0-libav.inc
SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-libav;name=base \
	git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
	file://0001-Disable-yasm-for-libav-when-disable-yasm.patch \
"

S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV_FORMAT = "base"

inherit gitpkgv
PV = "1.12.00+git${SRCPV}"
PKGV = "1.12.00+git${GITPKGV}"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}
