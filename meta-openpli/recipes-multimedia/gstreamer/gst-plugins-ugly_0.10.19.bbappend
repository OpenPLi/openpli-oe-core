PRINC = "3"

SRC_URI += "file://dvdsubdec-addproperty-singlebuffer.patch"

DEPENDS += "opencore-amr libcdio"

EXTRA_OECONF += "--enable-orc --with-plugins="

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"
