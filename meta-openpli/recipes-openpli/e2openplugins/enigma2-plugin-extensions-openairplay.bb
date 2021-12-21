MODULE = "OpenAirPlay"
DESCRIPTION = "AirPlay and AirTunes server for enigma2"
RDEPENDS_${PN} = "${PYTHON_PN}-twisted-core ${PYTHON_PN}-twisted-web ${PYTHON_PN}-m2crypto \
                  ${PYTHON_PN}-biplist ${PYTHON_PN}-netclient ${PYTHON_PN}-avahi ${PYTHON_PN}-dbus \
                  ${PYTHON_PN}-core ${PYTHON_PN}-subprocess ${PYTHON_PN}-io ${PYTHON_PN}-xmlrpc \
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
