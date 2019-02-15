MGCAMVER = ""

require mgcamd.inc

SRC_URI[softcam.md5sum] = "ce1f8f9c71b3e9aab963ec0068ec72ea"
SRC_URI[softcam.sha256sum] = "a95d251f21901e35e343dbb4281a3ac126ff5531ea96ffb1c5afddd1c2d9f5a3"

DEPENDS += "zlib"
RDEPENDS_${PN} += "libcrypto-compat"
