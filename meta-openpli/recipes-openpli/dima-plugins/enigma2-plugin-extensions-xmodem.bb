DESCRIPTION = "plugin to connect to internet via any modems"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=00f286ed22b8ad579d0715884c7639a9"

PLUGINNAME = "enigma2-plugin-extensions-xmodem"

require dima-plugins.inc

RDEPENDS_${PN} = " \
	iptables \
	usb-modeswitch \
	usb-modeswitch-data \
	picocom \
	ppp \
	"
