require gstreamer1.0.inc
include gstreamer1.0-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d \
"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gstreamer;branch=${GST_BRANCH};name=base \
	file://deterministic-unwind.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
	file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
"

