PRINC = "3"

SRC_URI += "file://orc.m4-fix-location-of-orcc-when-cross-compiling.patch"

DEPENDS += "opencore-amr libcdio"

EXTRA_OECONF += "--enable-orc --with-plugins="

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
