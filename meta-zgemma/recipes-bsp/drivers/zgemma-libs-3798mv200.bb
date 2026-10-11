SRCDATE = "20200625"
SRCDATE:nand = "20260402"

require zgemma-libs.inc

SRC_URI[md5sum] = "${LIBS_MD5}"
SRC_URI[sha256sum] = "${LIBS_SHA256}"

LIBS_MD5 = "869fb177bcdac42ad2020674015330b7"
LIBS_SHA256 = "0688f48de6ab3bb39ee1729043404bbd6c83e6a12bf251b9d758a86b2dffa6ee"

LIBS_MD5:nand = "daebce4a5455adc3495e67cd25bdf5fa"
LIBS_SHA256:nand = "c570f05ea36a0b8c462de6d5c7e83f77d26ba635b8b5c8d268accd444cf9fdad"

COMPATIBLE_MACHINE = "^h8se$|^h9$|^h9se$|^h9combo$|^h9combose$|^h10$|^i55plus$|^i55se$"
