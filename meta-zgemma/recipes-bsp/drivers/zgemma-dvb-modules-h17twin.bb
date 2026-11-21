KV = "4.10.12"
SRCDATE = "20250930"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"


require zgemma-dvb-modules.inc

SRC_URI[arm.md5sum] = "0ce78219b0f85fd55be77a5923da46f6"
SRC_URI[arm.sha256sum] = "58c18835deff03021f39816416f42d74083a20ffaf5086e037e6622f4d8a5998"

COMPATIBLE_MACHINE = "^h17twin$"
