include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV_FORMAT = "base"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=master;name=base \
	git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
	file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
"

inherit gitpkgv
PV = "1.12.00+git${SRCPV}"
PKGV = "1.12.00+git${GITPKGV}"

CFLAGS_append += " -Wno-maybe-uninitialized -Wno-uninitialized "

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}


PACKAGESPLITFUNCS_append = " handle_soup_rename "

python handle_soup_rename () {
    d.setVar('RPROVIDES_gstreamer1.0-plugins-good-soup', 'gstreamer1.0-plugins-good-souphttpsrc')
    d.setVar('RREPLACES_gstreamer1.0-plugins-good-soup', 'gstreamer1.0-plugins-good-souphttpsrc')
    d.setVar('RCONFLICTS_gstreamer1.0-plugins-good-soup', 'gstreamer1.0-plugins-good-souphttpsrc')
}
