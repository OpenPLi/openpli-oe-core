SUMMARY = "Vuplus Specific plugins"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV = "6e2ef83a9d63faa57c7266d7331b0ca9b9f4c9f9"
PR = "r1"

DEPENDS = "enigma2-transtreamproxy-util"
RDEPENDS = "enigma2-transtreamproxy-util enigma2"

BRANCH = "vuplus_experimental"

S = "${WORKDIR}/git"

inherit git-project
SRC_URI = " \
	git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH};tag=${SRCREV} \
	file://vuplus-transcodingsetup-plugin_20130108.patch;striplevel=1 \
"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/TransCodingSetup
	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/TransCodingSetup/*.py ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/TransCodingSetup
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

FILES_enigma2-plugin-systemplugins-transcodingsetup = "/usr/lib/enigma2/python/Plugins/SystemPlugins/TransCodingSetup"
PACKAGES = "enigma2-plugin-systemplugins-transcodingsetup"
PROVIDES = "${PACKAGES}"
PACKAGE_ARCH := "${MACHINE_ARCH}"
