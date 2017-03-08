require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "f4a2397cd39f92fe509206b9ddef2f8e"
SRC_URI[modules.sha256sum] = "7cd4459d44d7977be1a5ebeed07b46cef01cdfc15540c5223d2be03293123078"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "dm7020hd"
