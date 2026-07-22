inherit gitpkgv

PV = "1.5+git"
PKGV = "1.5+git${GITPKGV}"

SRC_URI = "git://github.com/LibreELEC/dvb-firmware.git;protocol=https;branch=master"
S = "${WORKDIR}/git/firmware"

require linux-firmware-local.inc

RDEPENDS:${PN} = "enigma2-plugin-drivers-ct2-dvb-usb-pctv292e"

DESCRIPTION = "Firmware for demod si2168 d60-01"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-demod-si2168-d60-01.fw ${D}${base_libdir}/firmware
}
