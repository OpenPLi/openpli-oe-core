# make sure it starts automatically after installation
INITSCRIPT_NAME = "openvpn"
INITSCRIPT_PARAMS = "defaults"

inherit update-rc.d
