KV = "4.4.176"
SRCDATE = "20261118"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"

require hd-dvb-modules-hisil.inc

SRC_URI[md5sum] = "0ded6de4e2b2fc7708b76494aeb3d863"
SRC_URI[sha256sum] = "10d7ff06a0c96cf37c82db5da16953a2ba37aed63773ee56e2c82f3358f4a7b0"
COMPATIBLE_MACHINE = "hd61"
