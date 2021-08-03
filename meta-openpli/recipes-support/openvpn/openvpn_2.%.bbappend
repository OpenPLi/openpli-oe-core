# make sure it starts automatically after installation
INITSCRIPT_NAME = "openvpn"
INITSCRIPT_PARAMS = "defaults"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit update-rc.d
