MODULE = "HetWeer"
DESCRIPTION = "HetWeer plugin"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/${MODULE}"
do_install() {
	install -d ${D}${PLUGINPATH}/Images
	cp -r ${S}/plugin/* ${D}${PLUGINPATH}
	chmod a+rX ${D}${PLUGINPATH}
}

# Just a quick hack to "compile" the python parts.
do_compile:append() {
    python3 -O -m compileall ${S}
}

pkg_postrm:${PN}() {
	rm -rf ${libdir}/enigma2/python/Plugins/Extensions/HetWeer
}

FILES:${PN} = "${PLUGINPATH}"

SRC_URI:append = " file://replace-distutils-with-setuptools.patch"
