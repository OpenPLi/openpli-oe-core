include gstreamer1.0-plugins-ugly.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068 "

S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV = "d2374716e2cf8d7ecf9b478e5c13e2461cab0a4d"
SRCREV_common = "29046b89d80bbca22eb222c18820fb40a4ac5bde"
SRCREV_FORMAT = "base"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=master \
	git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
"

inherit gitpkgv
PV = "1.12.00+git${SRCPV}"
PKGV = "1.12.00+git${GITPKGV}"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

