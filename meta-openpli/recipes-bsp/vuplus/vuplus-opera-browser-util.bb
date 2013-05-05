DESCRIPTION = "opera-hbbtv-browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

DEPENDS = "tslib mpfr gmp"

SRC_DATE = "20130425_pli0"
SRC_URI = "http://code.vuplus.com/download/build.fc3abf29fb03f797e78f907928125638/embedded/opera-sdk-build-package/opera-hbbtv_${SRC_DATE}.tar.gz"

PR = "r3_${SRC_DATE}"

S = "${WORKDIR}/opera-hbbtv"

do_install() {
	install -d ${D}/usr
	install -d ${D}/usr/local
	install -d ${D}/usr/local/hbb-browser

	mv ${S}/opera/lib/libopera.so ${S}/opera/lib/libopera.so.0
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

do_package_qa() {
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} = "/usr/lib /usr/local /usr/share /usr/bin /etc "
PACKAGES = "${PN}"
PROVIDES="${PACKAGES}"
PACKAGE_ARCH := "${MACHINE_ARCH}"

SRC_URI[md5sum] = "2e16afc3db22f80cf99de3bfb891fa06"
SRC_URI[sha256sum] = "41bbf4cda0b19ca7c79cb06ec4ab8cafee62fd9944d17e71978d4c9e6d72d264"
