SUMMARY = "Kodi six addon."
HOMEPAGE = "https://github.com/romanvm/kodi.six"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff3103b5db8ba4e2c66c511b7a73e407"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "0.13.1"

SRCREV = "2810f1ffd0f4ace0b37735d97a16f5defc36c608"
SRC_URI = "git://github.com/romanvm/kodi.six.git;branch=master;protocol=https"

S = "${WORKDIR}/git/${KODIADDONNAME}"

KODIADDONNAME = "script.module.kodi-six"
KODIADDONDIR = "${datadir}/kodi/addons"

do_compile() {
	:
}

do_install() {
	install -d ${D}${KODIADDONDIR}/${KODIADDONNAME}
	sed -i "s|2.26.0|3.0.0|" ${S}/addon.xml
	install -m644 ${S}/icon.png ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m644 ${S}/addon.xml ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m644 ${S}/LICENSE.txt ${D}${KODIADDONDIR}/${KODIADDONNAME}
	cp -rf ${S}/libs ${D}${KODIADDONDIR}/${KODIADDONNAME}
}

FILES:${PN} = "${KODIADDONDIR}"

