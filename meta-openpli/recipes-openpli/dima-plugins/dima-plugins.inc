HOMEPAGE = "https://github.com/Dima73/${PLUGINNAME}"

SRC_URI = "git://github.com/Dima73/${PLUGINNAME}.git;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv distutils-openplugins python3-compileall

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

do_install_append() {
	if ${@bb.utils.contains('PLUGIN_PATH','','true','false',d)}; then 
		install -d ${D}/${libdir}/enigma2/python/Plugins/${PLUGIN_PATH}/locale
		cp -rf ${S}/build/lib/${PLUGIN_PATH}/locale/ ${D}/${libdir}/enigma2/python/Plugins/${PLUGIN_PATH}/
	fi
}

