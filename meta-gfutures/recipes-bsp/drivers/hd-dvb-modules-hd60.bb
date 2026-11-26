KV = "4.4.176"
SRCDATE = "20261126"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"

require hd-dvb-modules-hisil.inc

SRC_URI[md5sum] = "fd9f7b00d03c1ebc6dff35fcd249301b"
SRC_URI[sha256sum] = "f533673275e129c92bf05f421670107d51f4b3653d10596784c8c9f682a0cae5"
COMPATIBLE_MACHINE = "hd60"
