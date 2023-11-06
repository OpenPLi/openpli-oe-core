SUMMARY = "WS-Discovery daemon"
DESCRIPTION = "Anounces a device to Windows via WSD"
LICENSE = "GPL-2.0-only"

require conf/license/license-gplv2.inc

DEPENDS = "util-linux samba"

SRC_URI = " \
    file://Makefile \
    file://wsdd.c \
    "

PV = "1.08"

S = "${WORKDIR}"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    make -f Makefile
}

do_install() {
    install -d ${D}/${sbindir}
    install -m 755 ${S}/wsdd ${D}/${sbindir}
}
