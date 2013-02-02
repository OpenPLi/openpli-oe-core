DEPENDS := "${@oe_filter_out('^(gtk+|gconf)$', '${DEPENDS}', d)}"

PRINC = "3"

SRC_URI += "file://audioparser-raise-ranks.patch"

EXTRA_OECONF += "--enable-orc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"
