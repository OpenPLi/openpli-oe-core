require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "05b139300567c0eae067d3296ee20649"
SRC_URI[modules.sha256sum] = "a2ceb2bd3e001c4917c798c4161e5f9d4776183e283d9c859bc5c58b5b3c6267"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "dm7020hd"
