KV = "4.10.12"
SRCDATE = "20250429"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"


require zgemma-dvb-modules.inc

SRC_URI[arm.md5sum] = "bf50c1df010901060fb4b25256207e2d"
SRC_URI[arm.sha256sum] = "3fdf41544b628b183404fd25903068f7ed1bff05200fac5311a0e2ceb089aaa4"

COMPATIBLE_MACHINE = "^h17$"
