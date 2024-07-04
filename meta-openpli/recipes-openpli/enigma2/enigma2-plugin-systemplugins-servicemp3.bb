DESCRIPTION = "servicemp3 for enigma2"
AUTHOR = "OpenPLi team <info@openpli.org>"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES += "virtual/enigma2-mediaservice"
RPROVIDES:${PN} += "virtual-enigma2-mediaservice"

GST_BASE_RDEPS = "\
	gstreamer1.0-plugins-base-alsa \
	gstreamer1.0-plugins-base-app \
	gstreamer1.0-plugins-base-audioconvert \
	gstreamer1.0-plugins-base-audiorate \
	gstreamer1.0-plugins-base-audioresample \
	gstreamer1.0-plugins-base-vorbis \
	gstreamer1.0-plugins-base-ogg \
	gstreamer1.0-plugins-base-opus \
	gstreamer1.0-plugins-base-playback \
	gstreamer1.0-plugins-base-rawparse \
	gstreamer1.0-plugins-base-subparse \
	gstreamer1.0-plugins-base-typefindfunctions \
	gstreamer1.0-plugins-base-videoconvertscale \
	"

GST_GOOD_RDEPS = "\
	gstreamer1.0-plugins-good-apetag \
	gstreamer1.0-plugins-good-audioparsers \
	gstreamer1.0-plugins-good-autodetect \
	gstreamer1.0-plugins-good-avi \
	gstreamer1.0-plugins-good-flac \
	gstreamer1.0-plugins-good-flv \
	gstreamer1.0-plugins-good-icydemux \
	gstreamer1.0-plugins-good-id3demux \
	gstreamer1.0-plugins-good-isomp4 \
	gstreamer1.0-plugins-good-matroska \
	gstreamer1.0-plugins-good-mpg123 \
	gstreamer1.0-plugins-good-rtp \
	gstreamer1.0-plugins-good-rtpmanager \
	gstreamer1.0-plugins-good-rtsp \
	gstreamer1.0-plugins-good-soup \
	gstreamer1.0-plugins-good-udp \
	gstreamer1.0-plugins-good-wavparse \
	"

GST_BAD_RDEPS = "\
	gstreamer1.0-plugins-bad-autoconvert \
	gstreamer1.0-plugins-bad-dash \
	gstreamer1.0-plugins-bad-faad \
	gstreamer1.0-plugins-bad-hls \
	gstreamer1.0-plugins-bad-mpegpsdemux \
	gstreamer1.0-plugins-bad-mpegtsdemux \
	gstreamer1.0-plugins-bad-opusparse \
	gstreamer1.0-plugins-bad-rtmp \
	gstreamer1.0-plugins-bad-smoothstreaming \
	gstreamer1.0-plugins-bad-videoparsersbad \
	"

GST_UGLY_RDEPS = "\
	gstreamer1.0-plugins-ugly-amrnb \
	gstreamer1.0-plugins-ugly-amrwbdec \
	gstreamer1.0-plugins-ugly-asf \
	gstreamer1.0-plugins-ugly-cdio \
	gstreamer1.0-plugins-ugly-dvdsub \
	"

DEPENDS = "\
	enigma2 \
	gstreamer1.0-plugins-base gstreamer1.0 \
	"

RDEPENDS:${PN} = "\
	enigma2 \
	glib-networking \
	gstreamer1.0-plugin-subsink \
	virtual-gstreamer1.0-dvbmediasink \
	${GST_BASE_RDEPS} \
	${GST_GOOD_RDEPS} \
	${GST_BAD_RDEPS} \
	${GST_UGLY_RDEPS} \
	libsoup-2.4 \
	"

RRECOMMENDS:${PN} = "\
	gstreamer1.0-plugins-good-pulse \
	"

SRC_URI = "git://github.com/openpli/servicemp3.git;branch=python3;protocol=https file://remove-redundant-c17-check.patch"

S = "${WORKDIR}/git"

inherit autotools gitpkgv pkgconfig python3-compileall

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

FILES:${PN} = "${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceMP3/"
