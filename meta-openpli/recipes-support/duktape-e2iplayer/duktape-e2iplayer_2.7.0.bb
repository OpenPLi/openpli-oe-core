DESCRIPTION = "Duktape is an embeddable Javascript engine, with a focus on portability and compact footprint."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b7825df97b52f926fc71300f7880408"

SRC_URI = " \
	http://duktape.org/duktape-${PV}.tar.xz \
	file://iptvplayer.patch \
	"

SRC_URI[md5sum] = "b3200b02ab80125b694bae887d7c1ca6"
SRC_URI[sha256sum] = "90f8d2fa8b5567c6899830ddef2c03f3c27960b11aca222fa17aa7ac613c2890"

S = "${WORKDIR}/duktape-${PV}"

do_compile() {
	oe_runmake -f Makefile.cmdline
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/duk ${D}${bindir}/
}

FILES:${PN} = "${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/bin ${bindir}/duk"
