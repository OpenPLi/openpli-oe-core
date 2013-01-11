require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "8826407d140bf732c42f58a21966e561"
SRC_URI[modules.sha256sum] = "efc7fbe2dce0c1d8ef9d48c7cb462baa958380c6d4434e7b78e2b4d0d9b39040"

RDEPENDS_${PN} += "kernel-module-stv0299"
