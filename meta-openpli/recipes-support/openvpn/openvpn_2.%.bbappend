# make sure it starts automatically after installation
INITSCRIPT_NAME = "openvpn"
INITSCRIPT_PARAMS = "defaults"

SRC_URI = "file://openvpn"

inherit update-rc.d
