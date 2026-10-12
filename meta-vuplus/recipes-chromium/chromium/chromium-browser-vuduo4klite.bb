SUMMARY = "Chromium and YoutubeTV"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"
PACKAGE_ARCH := "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

SRCDATE = "20230405_r1"

PV = "1.4"
PR = "${SRCDATE}"

# not available at code.vuplus.com
SRC_URI = "https://code.vuplus.de/download/chromium/chromium-vuplus-${MACHINE}-${SRCDATE}.tar.gz"

SRC_URI[md5sum] = "0779f182e7eea90ad7051a571a942d3d"
SRC_URI[sha256sum] = "4b11482b4a0641994b07f107368eb53bbd0e6fce008e5fb19158b389c3132086"

PREFERRED_VERSION_nss = "3.17.3"

DEPENDS = "nss"
RDEPENDS:${PN} = "nspr nss libxslt libcurl libudev libcrypto-compat-1.0.0 libxml2-qt"
RDEPENDS:${PN} += "libgles-${MACHINE}"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/usr/local/chromium
	cp -afR  --no-preserve=ownership ${S}/chromium/* ${D}/usr/local/chromium/
	chmod 755 ${D}/usr/local/chromium/run.sh
	chmod 755 ${D}/usr/local/chromium/bin/browser_shell
	chmod 755 ${D}/usr/local/chromium/bin/chrome
	chmod 755 ${D}/usr/local/chromium/bin/chrome-sandbox
	chmod 755 ${D}/usr/local/chromium/bin/nxconfig
	install -d ${D}/usr/local/lib
	ln -s /usr/lib/libv3ddriver.so ${D}/usr/local/lib/libv3ddriver.so
}

do_package_qa() {
}

PROVIDES += "virtual/chromium-browser"
RPROVIDES:${PN} += "virtual-chromium-browser"
PROVIDES += "chromium-browser"
RPROVIDES:${PN} += "chromium-browser"

FILES:${PN} = "/"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} += "already-stripped"
