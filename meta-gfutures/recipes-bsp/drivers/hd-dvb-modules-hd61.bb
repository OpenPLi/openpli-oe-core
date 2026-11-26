KV = "4.4.176"
SRCDATE = "20261126"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"

require hd-dvb-modules-hisil.inc

SRC_URI[md5sum] = "375f9cdaf02679637db608710134ee58"
SRC_URI[sha256sum] = "86347956f36027f5355a1c1df4b0310253896b5a539de05d63ce6fa439a5b12f"
COMPATIBLE_MACHINE = "hd61"
