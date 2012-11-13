DESCRIPTION = "opera-hbbtv-browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

DEPENDS = "tslib"
RDEPENDS = "tslib-conf"

SRC_DATE = "20121019_1"
SRC_URI = "http://code.vuplus.com/download/build.fc3abf29fb03f797e78f907928125638/embedded/opera-sdk-build-package/opera-hbbtv_${SRC_DATE}.tar.gz"

PR = "r2"

S = "${WORKDIR}/opera-hbbtv"

do_install() {
	install -d ${D}/usr
	install -d ${D}/usr/local
	install -d ${D}/usr/local/hbb-browser
	cp -avR ${S}/opera/* ${D}/usr/local/hbb-browser/

	install -d ${D}/etc
	cp -avR ${S}/dfb/etc/* ${D}/etc/

	install -d ${D}/usr/bin
	cp -avR ${S}/dfb/usr/bin/* ${D}/usr/bin/

	install -d ${D}/usr/lib
	cp -avR ${S}/dfb/usr/lib/* ${D}/usr/lib/

	install -d ${D}/usr/share
	cp -avR ${S}/dfb/usr/share/* ${D}/usr/share/

	install -d ${D}/usr/lib/enigma2
	install -d ${D}/usr/lib/enigma2/python
	install -d ${D}/usr/lib/enigma2/python/Plugins
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	cp -avR ${S}/plugin/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} = "/"
PACKAGES = "${PN}"

PACKAGE_ARCH := "${MACHINE_ARCH}"

PROVIDES="${PACKAGES}"

SRC_URI[md5sum] = "d79ecfbf4b6022d17cc9e5e37d4fd404"
SRC_URI[sha256sum] = "e9d0f8bfb3f6bfb15c88f0c4c845e798a773ca3e90e02607f75cf101cef08a2f"


