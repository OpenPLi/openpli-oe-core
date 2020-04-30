DESCRIPTION = "DLNA Server plugin"
MAINTAINER = "OpenPLi"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"

inherit pythonnative

PV = "0.2"

SRC_URI = "file://__init__.py \
           file://plugin.py \
"
RDEPENDS_${PN} = "minidlna"

S = "${WORKDIR}"
FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer/*"
PACKAGES = "${PN}"

do_install() {
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer
	install -m 0644 ${S}/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer/
	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}
