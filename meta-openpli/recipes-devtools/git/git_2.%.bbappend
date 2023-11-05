SRC_URI += " \
	file://receive_timeout.patch \
	"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
