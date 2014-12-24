DESCRIPTION = "gstreamer libxtrend plugin"
LICENSE = "CLOSED"
SECTION = "multimedia"
PRIORITY = "optional"

SRCREV = "568b515fa806c83e03ffe21e284da1e8624f7758"
SRC_URI = "git://github.com/et-plugins/gst-plugin-libxt.git;protocol=git"
S = "${WORKDIR}/git"

inherit gitpkgv

PV = "0.10.0+git${SRCPV}"
PKGV = "0.10.0+git${GITPKGV}"

FILES_${PN} = "${libdir}/gstreamer-0.10/"

do_install_append() {
	install -d ${D}${libdir}/gstreamer-0.10
	install -m 0755 ${S}/libgstxt.so ${D}${libdir}/gstreamer-0.10
}
