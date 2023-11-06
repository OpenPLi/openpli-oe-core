DESCRIPTION = "gstreamer subsink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "gstreamer gst-plugins-base"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

inherit gitpkgv

PV = "0.10.0+git${SRCPV}"
PKGV = "0.10.0+git${GITPKGV}"

SRC_URI = "git://github.com/OpenPLi/${BPN}.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES:${PN} = "${libdir}/gstreamer-0.10/*.so*"
FILES:${PN}-dev += "${libdir}/gstreamer-0.10/*.la"
FILES:${PN}-staticdev += "${libdir}/gstreamer-0.10/*.a"
FILES:${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"
