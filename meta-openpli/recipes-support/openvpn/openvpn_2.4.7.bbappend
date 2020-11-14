# Replace init script with ours, so the tun device is created
# before OpenVPN server starts
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

INITSCRIPT_NAME = "openvpn"
