require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "b09c7879a8fd5f205bd154668f6cf276"
SRC_URI[modules.sha256sum] = "00c7d8b819a79b6b3584992b9b1861b3b7de8343a2e5b618241de671f727912b"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "dm7020hd"
