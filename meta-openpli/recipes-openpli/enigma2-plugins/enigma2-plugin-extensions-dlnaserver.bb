DESCRIPTION = "DLNA Server plugin"
MAINTAINER = "OpenPLi"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"

inherit python3native

PV = "0.2"

SRC_URI = "file://__init__.py \
           file://plugin.py \
"
RDEPENDS:${PN} = "minidlna"

S = "${WORKDIR}"
FILES:${PN} = "${libdir}/enigma2/python/Plugins/Extensions/DLNAServer/*"
PACKAGES = "${PN}"

do_install() {
	install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/DLNAServer
	install -m 0644 ${S}/*.py ${D}${libdir}/enigma2/python/Plugins/Extensions/DLNAServer/
	python3 -O -m compileall ${D}${libdir}/enigma2/python/Plugins/
}
