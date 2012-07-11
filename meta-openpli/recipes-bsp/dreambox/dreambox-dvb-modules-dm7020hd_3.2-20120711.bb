require dreambox-dvb-modules.inc

PR = "${INC_PR}.0"

SRC_URI[modules.md5sum] = "1891a6bd64cdfa4d11bad00fa81fbead"
SRC_URI[modules.sha256sum] = "dae4a5a0a7f947dfc02b6658b612d8240193aa5ccd0d9c5bae3853d301b9475a"

RDEPENDS_${PN} += "kernel-module-stv0299"
