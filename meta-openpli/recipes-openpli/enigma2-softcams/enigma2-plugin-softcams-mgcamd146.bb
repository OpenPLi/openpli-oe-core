MGCAMVER = ""

PV = "1.46"

require mgcamd.inc

SRC_URI[softcam.md5sum] = "71ea33c25346ccd4a3c382137e9b466e"
SRC_URI[softcam.sha256sum] = "b70a28fb6d3ac59cd16a6d4681d81ef761d2f5c56b0620c1f65a4be1f4308da0"

DEPENDS += "zlib"
RDEPENDS_${PN} += "libcrypto-compat libxcrypt-compat"

RCONFLICTS_${PN} = "enigma2-plugins-softcams-mgcamd"
RREPLACES_${PN} = "enigma2-plugins-softcams-mgcamd"
