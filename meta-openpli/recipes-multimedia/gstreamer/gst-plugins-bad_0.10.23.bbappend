DEPENDS := "${@oe_filter_out('librsvg', '${DEPENDS}', d)}"

PRINC = "3"

SRC_URI += " \
        file://0003-mpegpsdemux_speedup.diff.patch \
	file://0004-mpegdemux-compile-fixes.patch \
	file://0005-hlsdemux-locking-fixes.patch \
	file://0006-hlsdemux-backport.patch \
	file://orc.m4-fix-location-of-orcc-when-cross-compiling.patch \
"
DEPENDS += "libmms librtmp"

EXTRA_OECONF += "--enable-orc --with-plugins="
# fix "configure: error: cannot run test program while cross compiling"
EXTRA_OECONF += "--disable-mpeg2enc --disable-mplex"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
