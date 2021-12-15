MODULE = "OpenWebif"
DESCRIPTION = "Control your receiver with a browser"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;md5=26abba37d1c2fcbf96a087ceb8e1db86"

DEPENDS = "${PYTHON_VER}-cheetah-native"
RDEPENDS_${PN} = "\
	aio-grab \
	${PYTHON_VER}-cheetah \
	${PYTHON_VER}-compression\
	${PYTHON_VER}-ipaddress\
	${PYTHON_VER}-json \
	${PYTHON_VER}-misc \
	${PYTHON_VER}-numbers \
	${PYTHON_VER}-pprint \
	${PYTHON_VER}-pyopenssl \
	${PYTHON_VER}-shell \
	${PYTHON_VER}-six \
	${PYTHON_VER}-twisted-web \
	${PYTHON_VER}-unixadmin \
	"

inherit gittag
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

require openplugins-distutils.inc

# Just a quick hack to "compile" it
do_compile() {
	cheetah-compile -R --nobackup ${S}/plugin
	python2 -O -m compileall -d ${PLUGINPATH} ${S}/plugin
}

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/${MODULE}"
do_install_append() {
	install -d ${D}${PLUGINPATH}
	cp -r ${S}/plugin/* ${D}${PLUGINPATH}
	chmod a+rX ${D}${PLUGINPATH}
}

FILES_${PN}-src += "${PLUGINPATH}/controllers/views/*.tmpl ${PLUGINPATH}/controllers/views/*/*.tmpl ${PLUGINPATH}/controllers/views/*/*/*.tmpl"
FILES_${PN} = "${PLUGINPATH}"
