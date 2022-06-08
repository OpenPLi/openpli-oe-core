MODULE = "HetWeer"
DESCRIPTION = "HetWeer plugin"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/${MODULE}"

# Just a quick hack to "compile" it
do_compile() {
	python -O -m compileall -d ${PLUGINPATH} ${S}/plugin
}

do_install() {
	install -d ${D}${PLUGINPATH}/Images
	cp -r ${S}/plugin/* ${D}${PLUGINPATH}
	chmod a+rX ${D}${PLUGINPATH}
}


pkg_postrm_${PN}() {
	rm -rf ${libdir}/enigma2/python/Plugins/Extensions/HetWeer
}

FILES_${PN} = "${PLUGINPATH}"
