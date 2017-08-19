require gstreamer1.0-plugins-good.inc
include gstreamer1.0-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=${GST_BRANCH};name=base \
	file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
	file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
"

CFLAGS_append += " -Wno-maybe-uninitialized -Wno-uninitialized "

