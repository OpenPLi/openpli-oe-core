# make sure it starts automatically after installation
INITSCRIPT_NAME = "openvpn"
INITSCRIPT_PARAMS = "defaults"

RDEPENDS:{$PN} = "lzo lz4"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit update-rc.d
