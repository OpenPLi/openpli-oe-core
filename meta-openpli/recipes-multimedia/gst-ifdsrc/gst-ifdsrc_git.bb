DESCRIPTION = "A template for writing your own GStreamer plug-in"
MAINTAINER = "samsamsam"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

require conf/license/openpli-gplv2.inc
inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

SRC_URI = "https://gitlab.com/e2i/gst-ifdsrc.git;protocol=http"

SRC_URI[md5sum] = "ea911f389212459d849a2a286442bbda"
SRC_URI[sha256sum] = "bb0b01517431a6e116c94c6df94cd1d2e0fda9de994bd1e5be592aa8e29dd57c"

S = "${WORKDIR}/git/src"

FILES_${PN} += "${libdir}/gstreamer-1.0"
