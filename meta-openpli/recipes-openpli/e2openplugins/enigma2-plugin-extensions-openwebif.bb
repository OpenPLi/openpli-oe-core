MODULE = "OpenWebif"
DESCRIPTION = "Control your receiver with a browser"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;md5=26abba37d1c2fcbf96a087ceb8e1db86"

DEPENDS = "${PYTHON_PN}-cheetah-native"
RDEPENDS_${PN} = "\
	aio-grab \
	${PYTHON_PN}-cheetah \
	${PYTHON_PN}-compression \
	${PYTHON_PN}-ipaddress \
	${PYTHON_PN}-json \
	${PYTHON_PN}-misc \
	${PYTHON_PN}-numbers \
	${PYTHON_PN}-pprint \
	${PYTHON_PN}-pyopenssl \
	${PYTHON_PN}-shell \
	${PYTHON_PN}-six \
	${PYTHON_PN}-twisted-web \
	${PYTHON_PN}-unixadmin \
	"

inherit gittag
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

require openplugins-distutils.inc

# Just a quick hack to "compile" it
do_compile() {
	cheetah-compile -R --nobackup ${S}/plugin
	python3 -O -m compileall -d ${PLUGINPATH} ${S}/plugin
}

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/${MODULE}"
do_install_append() {
	install -d ${D}${PLUGINPATH}
	cp -r ${S}/plugin/* ${D}${PLUGINPATH}
	chmod a+rX ${D}${PLUGINPATH}
}

FILES_${PN}-src += "${PLUGINPATH}/controllers/views/*.tmpl ${PLUGINPATH}/controllers/views/*/*.tmpl ${PLUGINPATH}/controllers/views/*/*/*.tmpl"
FILES_${PN} = "${PLUGINPATH}"
