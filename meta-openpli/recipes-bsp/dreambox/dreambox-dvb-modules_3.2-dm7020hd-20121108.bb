require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "561ba4b625bcd807aea3492b68840081"
SRC_URI[modules.sha256sum] = "e31b81cf90b7a7f887de6700f381f679fb497bdcb3d4a8f523c9c6e371c5eb41"

RDEPENDS_${PN} += "kernel-module-stv0299"
