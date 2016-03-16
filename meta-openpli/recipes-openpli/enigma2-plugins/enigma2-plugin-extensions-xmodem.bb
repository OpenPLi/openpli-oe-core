DESCRIPTION = "plugin to connect to internet via any modems"
HOMEPAGE = "https://github.com/Dima73/enigma2-plugin-extensions-xmodem"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=00f286ed22b8ad579d0715884c7639a9"
SRC_URI = "git://github.com/Dima73/enigma2-plugin-extensions-xmodem.git"
S = "${WORKDIR}/git"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit allarch distutils-openplugins

RDEPENDS_${PN} = " \
	iptables \
	libusb-0.1-4 (>= 0.1.5) \
	libc6 (>= 2.19) \
	libpcap1 (>= 1.5.3) \
	usb-modeswitch \
	usb-modeswitch-data \
	picocom \
	ppp \
	"
