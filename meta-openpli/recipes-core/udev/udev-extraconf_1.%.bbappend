FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://mount.sh \
	file://automount.rules \
	file://localextra.rules \
	"
