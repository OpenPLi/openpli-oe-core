include gstreamer1.0-plugins-ugly.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068 "

S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV_FORMAT = "base"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=master;name=base \
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

