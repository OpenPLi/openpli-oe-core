DEPENDS := "${@oe_filter_out('^(gconf|pulseaudio)$', '${DEPENDS}', d)}"
DEPENDS := "${@oe_filter_out('gtk+', '${DEPENDS}', d)}"

PRINC = "3"

SRC_URI += "file://orc.m4-fix-location-of-orcc-when-cross-compiling.patch \
            file://0001-accept-substream-syncwords-DTS-HD.patch \
            ${@base_contains('MACHINE_FEATURES', 'legacykernel', 'file://v4l-compile-fix-old-kernel.patch', '', d)}"

EXTRA_OECONF += "--enable-orc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
