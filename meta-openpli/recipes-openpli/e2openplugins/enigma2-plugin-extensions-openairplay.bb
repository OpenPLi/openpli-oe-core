MODULE = "OpenAirPlay"
DESCRIPTION = "AirPlay and AirTunes server for enigma2"
RDEPENDS_${PN} = "${PYTHON_VER}-twisted-core ${PYTHON_VER}-twisted-web ${PYTHON_VER}-m2crypto \
                  ${PYTHON_VER}-biplist ${PYTHON_VER}-netclient ${PYTHON_VER}-avahi ${PYTHON_VER}-dbus \
                  ${PYTHON_VER}-core ${PYTHON_VER}-subprocess ${PYTHON_VER}-io ${PYTHON_VER}-xmlrpc \
                  hairtunes"

inherit gitpkgv
PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0.2"

require conf/license/license-gplv2.inc
require openplugins.inc

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/${MODULE}"
do_install() {
	install -d ${D}${PLUGINPATH}
	cp -r --preserve=mode,links ${S}/plugin/* ${D}${PLUGINPATH}
}

FILES_${PN} = "${PLUGINPATH}"
