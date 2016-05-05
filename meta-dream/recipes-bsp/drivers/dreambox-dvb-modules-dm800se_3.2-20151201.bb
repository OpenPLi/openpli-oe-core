require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "46d4c11fb3704a70ae57b1f0dadfd6de"
SRC_URI[modules.sha256sum] = "f34897dd856e50bbd8fb7df203d649230da2b3040713f7a6c882f306bcb4e8db"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "dm800se"
