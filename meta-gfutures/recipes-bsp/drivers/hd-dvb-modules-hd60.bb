KV = "4.4.176"
SRCDATE = "20261118"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"

require hd-dvb-modules-hisil.inc

SRC_URI[md5sum] = "e336b8d1409c1457a36a81b6a1401095"
SRC_URI[sha256sum] = "d7c4c176f169d7963127e4a6077431c265cb134cfa756841d46b760bdfe18042"
COMPATIBLE_MACHINE = "hd60"
