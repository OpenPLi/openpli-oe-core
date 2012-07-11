require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "27c9da9d2d52d97f497b7bf09f976d65"
SRC_URI[modules.sha256sum] = "fa7d8a1f7d68cd245c2e77847a8ba632c3ef6a8b83aca729a8b9e052810d05fc"

RDEPENDS_${PN} += "kernel-module-stv0299"
