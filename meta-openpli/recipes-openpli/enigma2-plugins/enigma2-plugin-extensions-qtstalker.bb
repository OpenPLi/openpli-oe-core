SUMMARY = "Stalker for QT browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
VER ?= "${@bb.utils.contains('MACHINE_FEATURES', 'hisil', '-v2', '', d)}"

SRC_URI = "git://github.com/zgemma-star/e2plugins.git;protocol=git"

PACKAGES = "${PN}"
RDEPENDS_${PN}  = "qtwebkit"

S = "${WORKDIR}/git/qtstalker${VER}"

QtStalker = "enigma2/python/Plugins/Extensions/QtStalker"

do_compile () {
}

FILES_${PN} =  " \
	${bindir} \
	/usr/lib/${QtStalker} \
	/usr/share/stalker \
"

do_install() {
	install -d ${D}/usr/lib/${QtStalker}
	install -d ${D}/usr/share/stalker
	cp -rp ${S}/usr/share/stalker/* ${D}/usr/share/stalker/
	chmod -R a+rX ${D}/usr/share/stalker/
	install -m 0755 ${S}/plugin/__init__.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/browser.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/datasocket.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/plugin.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/stalker.py ${D}/usr/lib/${QtStalker}
	install -m 0755 ${S}/plugin/stalker.png ${D}/usr/lib/${QtStalker}
	install -d ${D}/${bindir}
	install -m 0755 ${S}/stalker ${D}${bindir}
}

do_package_qa() {
}

PACKAGE_ARCH := "${MACHINE_ARCH}"

# prevent 'double stripping' our binaries, which will break them
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "already-stripped"