DESCRIPTION = "Duktape is an embeddable Javascript engine, with a focus on portability and compact footprint."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fddbe4ed8ac933555f193488d973da85"

SRC_URI = " \
	http://duktape.org/duktape-2.0.1.tar.xz \
	file://iptvplayer_2.0.1.patch \
	"

SRC_URI[md5sum] = "49ab65b6f5e3b0b794b3799a9dd0d251"
SRC_URI[sha256sum] = "3c09bb0ec2160c50c7a7a4705502674df56c0f710f2e793a8b5b1e236be56b89"

do_compile() {
	oe_runmake -f Makefile.cmdline
}

do_install() {
	install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/bin
	install -m 0755 ${S}/duk ${D}${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/bin
}

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/bin"
