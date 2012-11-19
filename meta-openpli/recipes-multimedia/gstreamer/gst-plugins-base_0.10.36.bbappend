DEPENDS += "cdparanoia orc orc-native"
PRINC = "2"

EXTRA_OECONF += "--enable-orc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"
