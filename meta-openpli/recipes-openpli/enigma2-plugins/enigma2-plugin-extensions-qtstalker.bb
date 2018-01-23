SUMMARY = "Stalker for QT browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/zgemma-star/e2plugins.git;protocol=git"

PACKAGES = "${PN}"
RDEPENDS_${PN}  = "qtwebkit"

S = "${WORKDIR}/git/qtstalker"

QtStalker = "enigma2/python/Plugins/Extensions/QtStalker"

do_compile () {
}

FILES_${PN} =  "/${bindir} \
	/usr/lib/mozilla/plugins \
	/usr/lib/${QtStalker} \
	"

do_install() {
	install -d ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/__init__.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/browser.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/datasocket.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/plugin.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/stalker.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/stalker.png ${D}/usr/lib/${QtStalker}
	install -d ${D}/${bindir}
	install -m 0755 ${S}/stalker ${D}/${bindir}
	install -d ${D}/usr/lib
	cd ${D}/usr/lib
	ln -sf ../share/fonts fonts
}

PACKAGE_ARCH := "${MACHINE_ARCH}"

# prevent 'double stripping' our binaries, which will break them
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
