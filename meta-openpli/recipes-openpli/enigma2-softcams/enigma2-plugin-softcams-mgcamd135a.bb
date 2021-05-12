MGCAMVER = "135a"

PV="1.35a"

require mgcamd.inc

SRC_URI[softcam.md5sum] = "9d247d7b426b5b9a080925dda9fa9594"
SRC_URI[softcam.sha256sum] = "892725a14f61e7195db48c69f51bcafa5cf9a106d253640654224a762ae4d437"

DEPENDS += "zlib"
RDEPENDS_${PN} += "libcrypto-compat libxcrypt-compat"
INSANE_SKIP_${PN} += "file-rdeps ldflags"

RCONFLICTS_${PN} = "enigma2-plugins-softcams-mgcamd"
RREPLACES_${PN} = "enigma2-plugins-softcams-mgcamd"
