DEPENDS += "cdparanoia orc orc-native"
PRINC = "3"

EXTRA_OECONF += "--enable-orc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"
