DESCRIPTION = "gstreamer subsink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "glib-2.0-native gstreamer1.0 gstreamer1.0-plugins-base"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "git://github.com/OpenPLi/gst-plugin-subsink.git;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv

GSTVERSION = "1.0"

PV = "${GSTVERSION}+git${SRCPV}"
PKGV = "${GSTVERSION}+git${GITPKGV}"

EXTRA_OECONF = "--with-gstversion=${GSTVERSION}"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-${GSTVERSION}/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-${GSTVERSION}/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-${GSTVERSION}/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-${GSTVERSION}/.debug"

