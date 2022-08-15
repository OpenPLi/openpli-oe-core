SUMMARY = "HbbTV for QT browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
VER ?= "${@bb.utils.contains('MACHINE_FEATURES', 'hisil', '-v2', '', d)}"

SRC_URI = "git://github.com/oe-alliance/e2plugins.git;protocol=https;branch=python3"

PACKAGES = "${PN}"
RDEPENDS_${PN}  = "qtwebkit"

S = "${WORKDIR}/git/qthbbtv${VER}"

QtHbbtv = "enigma2/python/Plugins/Extensions/QtHbbtv"

do_compile () {
}

FILES_${PN} =  " \
	${bindir} \
	${libdir}/mozilla/plugins \
	${libdir}/${QtHbbtv} \
"

do_install() {
	install -d ${D}${libdir}/${QtHbbtv}
	install -m 0755 ${S}/plugin/__init__.py ${D}${libdir}/${QtHbbtv}
	install -m 0755 ${S}/plugin/browser.py ${D}${libdir}/${QtHbbtv}
	install -m 0755 ${S}/plugin/datasocket.py ${D}${libdir}/${QtHbbtv}
	install -m 0755 ${S}/plugin/hbbtv.py ${D}${libdir}/${QtHbbtv}
	install -m 0755 ${S}/plugin/plugin.py ${D}${libdir}/${QtHbbtv}
	install -d ${D}${bindir}
	install -m 0755 ${S}/qthbbtv ${D}${bindir}
	install -d ${D}${libdir}/mozilla/plugins
	install -m 0755 ${S}/libnpapihbbtvplugin.so ${D}${libdir}/mozilla/plugins
}

do_package_qa() {
}

PACKAGE_ARCH := "${MACHINE_ARCH}"

# prevent 'double stripping' our binaries, which will break them
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "already-stripped dev-so"
