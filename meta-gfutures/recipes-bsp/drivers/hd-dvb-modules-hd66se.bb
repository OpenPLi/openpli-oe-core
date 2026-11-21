KV = "4.4.176"
SRCDATE = "20261118"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"

require hd-dvb-modules-hisil.inc

SRC_URI[md5sum] = "3de31a1deacc17b81b824a16a83ea7db"
SRC_URI[sha256sum] = "55fea1db5e781f1c4a4b8d004de7e57adc6c5fe2d1767606d23611ceafbd6401"
COMPATIBLE_MACHINE = "hd66se"
