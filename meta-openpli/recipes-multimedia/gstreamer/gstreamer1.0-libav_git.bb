include gstreamer1.0-libav.inc

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

DEPENDS =+ "ffmpeg"
PACKAGECONFIG = "orc libav"
CFLAGS_append = " -Wno-deprecated-declarations "

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV = "011dc043c8253c3c56caf2f34542e76bcb1f7dba"
SRCREV_common = "29046b89d80bbca22eb222c18820fb40a4ac5bde"
SRCREV_FORMAT = "base"

inherit gitpkgv
PV = "1.12.00+git${SRCPV}"
PKGV = "1.12.00+git${GITPKGV}"

MIPSFPU = "${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)}"

LIBAV_EXTRA_CONFIGURE_COMMON_ARG = "--target-os=linux \
  --cc='${CC}' --as='${CC}' --ld='${CC}' --nm='${NM}' --ar='${AR}' \
  --ranlib='${RANLIB}' \
  ${@bb.utils.contains("TARGET_ARCH", "arm", "", " \
  --disable-mipsdsp \
  --disable-mipsdspr0 \
  ${MIPSFPU}", d)} \
  ${GSTREAMER_1_0_DEBUG} \
  --cross-prefix='${HOST_PREFIX}'"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

