require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "87b363c003a4ac03614fb2562d770ddc"
SRC_URI[modules.sha256sum] = "f49721c5376fb1ee926e6ed5c5d2443999c2e44e9d00747e0e7b69a843da38dd"

RDEPENDS_${PN} += "kernel-module-stv0299"
