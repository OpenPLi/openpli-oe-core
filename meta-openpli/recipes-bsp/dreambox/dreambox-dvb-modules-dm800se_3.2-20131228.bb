require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "568eff61c284e206d8fd962f6e8459d5"
SRC_URI[modules.sha256sum] = "2f5d0235fe5252903b29a1e2ed1892a35d28efa8c7efc26c331b58660badb32d"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "dm800se"
