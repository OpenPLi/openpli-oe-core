SUMMARY = "Kodi inputstream addon for several manifest types"
HOMEPAGE = "https://github.com/emilsvennesson/script.module.inputstreamhelper"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e7d24110ae7397fb8d7bbe5265aac078"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit setuptools3

PV = "0.5.10"

SRCREV = "3c6bf7279a8cb596493a5922dacb7bd09719441d"
SRC_URI = " \
	git://github.com/emilsvennesson/script.module.inputstreamhelper.git;nobranch=1;protocol=https \
"

S = "${WORKDIR}/git"

KODIADDONNAME = "script.module.inputstreamhelper"
KODIADDONDIR = "${datadir}/kodi/addons"

do_install() {
	sed -i "s|2.25.0|3.0.0|" ${S}/addon.xml
	install -d ${D}${KODIADDONDIR}/${KODIADDONNAME}/lib
	install -m644 ${S}/addon.xml ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m755 ${S}/default.py ${D}${KODIADDONDIR}/${KODIADDONNAME}
	cp -rf ${S}/resources ${D}${KODIADDONDIR}/${KODIADDONNAME}
	cp -rf ${B}/lib/inputstreamhelper ${D}${KODIADDONDIR}/${KODIADDONNAME}/lib
}

FILES:${PN} = "${KODIADDONDIR}"

