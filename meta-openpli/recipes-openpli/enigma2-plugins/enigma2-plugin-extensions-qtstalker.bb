SUMMARY = "Stalker for QT browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
VER ?= "${@bb.utils.contains('MACHINE_FEATURES', 'hisil', '-v2', '', d)}"

SRC_URI = "git://github.com/zgemma-star/e2plugins.git;protocol=https"

PACKAGES = "${PN}"
RDEPENDS_${PN}  = "qtwebkit"

S = "${WORKDIR}/git/qtstalker${VER}"

QtStalker = "enigma2/python/Plugins/Extensions/QtStalker"

do_compile () {
}

FILES_${PN} =  " \
	${bindir} \
	${libdir}/${QtStalker} \
	${datadir}/stalker \
"

do_install() {
	install -d ${D}${libdir}/${QtStalker}
	install -d ${D}${datadir}/stalker
	cp -rp ${S}${datadir}/stalker/* ${D}${datadir}/stalker/
	chmod -R a+rX ${D}${datadir}/stalker/
	install -m 0755 ${S}/plugin/__init__.py ${D}${libdir}/${QtStalker}
	install -m 0755 ${S}/plugin/browser.py ${D}${libdir}/${QtStalker}
	install -m 0755 ${S}/plugin/datasocket.py ${D}${libdir}/${QtStalker}
	install -m 0755 ${S}/plugin/plugin.py ${D}${libdir}/${QtStalker}
	install -m 0755 ${S}/plugin/stalker.py ${D}${libdir}/${QtStalker}
	install -m 0755 ${S}/plugin/stalker.png ${D}${libdir}/${QtStalker}
	install -d ${D}/${bindir}
	install -m 0755 ${S}/stalker ${D}${bindir}
}

do_package_qa() {
}

PACKAGE_ARCH := "${MACHINE_ARCH}"

# prevent 'double stripping' our binaries, which will break them
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "already-stripped"
