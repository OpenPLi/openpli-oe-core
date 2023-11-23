MODULE = "OpenWebif"
DESCRIPTION = "Control your receiver with a browser"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README;md5=26abba37d1c2fcbf96a087ceb8e1db86"

DEPENDS = "python3-cheetah-native"
RDEPENDS:${PN} = "\
	aio-grab \
	python3-cheetah \
	python3-compression \
	python3-ipaddress \
	python3-json \
	python3-misc \
	python3-numbers \
	python3-pprint \
	python3-pyopenssl \
	python3-shell \
	python3-six \
	python3-twisted-web \
	python3-unixadmin \
	"

inherit gittag python3-compileall
PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-OpenWebif.git;protocol=https;branch=master \
		file://set-packages-explicit.patch \
"
S="${WORKDIR}/git"


# Just a quick hack to "compile" it
do_compile() {
	cheetah-compile -R --nobackup ${S}/plugin
	python3 -O -m compileall -d ${PLUGINPATH} ${S}/plugin
}

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/${MODULE}"
do_install:append() {
	install -d ${D}${PLUGINPATH}
	cp -r ${S}/plugin/* ${D}${PLUGINPATH}
	chmod a+rX ${D}${PLUGINPATH}
}

FILES:${PN}-src += "${PLUGINPATH}/controllers/views/*.tmpl ${PLUGINPATH}/controllers/views/*/*.tmpl ${PLUGINPATH}/controllers/views/*/*/*.tmpl"
FILES:${PN} = "${PLUGINPATH}"
