LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

PROVIDES += "vuplus-dvb-proxy"
RPROVIDES:${PN} += "vuplus-dvb-proxy"

ALLOW_EMPTY:${PN} = "1"

PACKAGE_ARCH := "${MACHINE_ARCH}"
PR = "r1"

# dummy
# duo4klite drivers are included in platform-util
