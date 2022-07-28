CCCAMVER = ""

require cccam.inc

SRC_URI[softcam.md5sum] = "80c05deecd7cea0d8b930caef399a4f5"
SRC_URI[softcam.sha256sum] = "1c130bb76b06f836a4ed8936cd205f01d90c2645cd13814e4c487a7296f04cdb"

INSANE_SKIP_${PN} = "file-rdeps already-stripped"
