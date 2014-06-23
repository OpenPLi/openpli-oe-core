DESCRIPTION = "Font Valis Hd"

require conf/license/openpli-gplv2.inc

PV = "1.0"
PR = "r0"
PACKAGE_ARCH = "all"

SRC_URI = "file://hd.ttf \
	file://hdi.ttf"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/usr/share/enigma2/fonts
	install -m 0644 ${S}/hd.ttf ${D}/usr/share/enigma2/fonts
	install -m 0644 ${S}/hdi.ttf ${D}/usr/share/enigma2/fonts
}

FILES_${PN} = "/usr/share/enigma2/fonts/*.ttf"
