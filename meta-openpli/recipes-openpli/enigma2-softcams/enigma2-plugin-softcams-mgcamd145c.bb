MGCAMVER = "145c"

PV = "1.45c"

require mgcamd.inc

SRC_URI[softcam.md5sum] = "0cfbada607630cb1a0a6d14cf38c0859"
SRC_URI[softcam.sha256sum] = "1aebc407b4c7c96554d4ded83a6dc83b90b7db9fe1975f063e6f941e0c93c61e"

DEPENDS += "zlib"
RDEPENDS_${PN} += "libcrypto-compat libxcrypt-compat"

RCONFLICTS_${PN} = "enigma2-plugins-softcams-mgcamd"
RREPLACES_${PN} = "enigma2-plugins-softcams-mgcamd"
