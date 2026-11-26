KV = "4.4.176"
SRCDATE = "20261126"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"

require hd-dvb-modules-hisil.inc

SRC_URI[md5sum] = "bad057461c5915da219a164ea3d0eda2"
SRC_URI[sha256sum] = "ede90d6f037a506814f15bde21ba932411cdf6d93cc1db0e20e6f6a4e634263a"
COMPATIBLE_MACHINE = "hd66se"
