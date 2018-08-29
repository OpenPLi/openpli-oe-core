MAINTAINER = "Narcis Ilisei"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c34afdc3adf82d2448f12715a255122"

PV = "v.02.28.10"
PR = ""

SRC_URI = "https://sourceforge.net/projects/inadyn-mt/files/inadyn-mt/inadyn-mt.${PV}/inadyn-mt.${PV}.tar.gz \
	file://inadyn-mt.sh \
	file://inadyn.conf \
	file://remove_host_include_paths.patch \
	"

SRC_URI[md5sum] = "96d193759d61ee620d059799119dd982"
SRC_URI[sha256sum] = "f69bea12d96b66f9f662a8df0730c60457b24f5fb5308b109936880ebf7be5ca"

S = "${WORKDIR}/inadyn-mt.${PV}"

inherit autotools-brokensep update-rc.d

INITSCRIPT_NAME = "inadyn-mt"
CONFFILES_${PN} = "/etc/inadyn.conf"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
	make -f makefile-deprecated
}

do_install() {
	install -d ${D}/usr/bin
	install -m 755 ${B}/bin/linux/inadyn-mt ${D}/usr/bin
	install -d ${D}/etc
	install -m 644 ${WORKDIR}/inadyn.conf ${D}/etc/
	install -d ${D}/etc/init.d
	install -m 755 ${WORKDIR}/inadyn-mt.sh ${D}/etc/init.d/inadyn-mt
}
