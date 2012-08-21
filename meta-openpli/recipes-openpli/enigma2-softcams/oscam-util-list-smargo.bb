DESCRIPTION = "List smargo utility"
LICENSE = "GPLv3"

PV = "svn${SRCPV}"
PR = "r1"

DEPENDS = "libusb"

INHIBIT_PACKAGE_STRIP = "1"

include oscamurl.inc

SRC_URI = " \
		svn://${OSCAMHOST}/svn/oscam/trunk;module=utils;proto=http;scmdata=keep \
		"

LIC_FILES_CHKSUM = "file://list_smargo.c;startline=5;endline=17;md5=d0df56ed6dc45b68c4946b217f2aeb84"

S = "${WORKDIR}/utils"

OECMAKE_SOURCEPATH = "${S}"
EXTRA_OEMAKE = "-c ${OECMAKE_SOURCEPATH} "


EXTRA_OECMAKE += "\
		-DOSCAM_SYSTEM_NAME=Tuxbox \
		-DSTATIC_LIBUSB=1 \
		"

inherit cmake

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/list_smargo ${D}/usr/bin/list_smargo
}
