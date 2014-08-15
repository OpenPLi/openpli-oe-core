require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "d507b37fec2c8062a34eb74367178dc2"
SRC_URI[modules.sha256sum] = "cb1d0921531b360852098251093670f6583424ef97389534a2094bd044dacc5e"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "dm800se"
