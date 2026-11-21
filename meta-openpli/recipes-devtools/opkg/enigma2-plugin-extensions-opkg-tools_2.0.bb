SUMMARY = "OPKG CLI pack and unpack tools"
MAINTAINER = "PLi team"
LICENSE = "CC-BY-NC-ND-4.0"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/meta-openpli/licenses/CC-BY-NC-ND-4.0;md5=8009795292660aa9c8da059e5b1581c1"

RDEPENDS:${PN} += "binutils zstd"

FILESEXTRAPATHS:prepend := "${THISDIR}/opkg-tools:"

SRC_URI = " \
	file://opkg-build \
	file://opkg-unbuild \
	"

do_install(){
	install -d ${D}/usr/bin
	install -m 755 ${WORKDIR}/opkg-build ${D}/usr/bin/opkg-build
	install -m 755 ${WORKDIR}/opkg-unbuild ${D}/usr/bin/opkg-unbuild
}
