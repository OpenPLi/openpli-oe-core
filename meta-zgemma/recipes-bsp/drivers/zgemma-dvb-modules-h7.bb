KV = "4.10.12"
SRCDATE = "20191123"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbs"


require zgemma-dvb-modules.inc

SRC_URI[arm.md5sum] = "e9ce3268385062b5da7f881ccf46ce65"
SRC_URI[arm.sha256sum] = "1f598fbf7d054c38425ee9f509d1e3add9df93122d6eda8964b4a78e441f238d"

COMPATIBLE_MACHINE = "^h7$"
