DEFAULT_PREFERENCE = "1"

SUMMARY = "rtmpdump Real-Time Messaging Protocol"
DESCRIPTION = "rtmpdump is a toolkit for RTMP streams. All forms of RTMP are \
supported, including rtmp://, rtmpt://, rtmpe://, rtmpte://, and rtmps://."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "openssl zlib"

inherit autotools-brokensep gitpkgv

SRCREV = "${AUTOREV}"
PKGV = "2.48+git${GITPKGV}"
PV = "2.48+git${SRCPV}"
PR = "r8"

SRC_URI = "git://github.com/oe-alliance/rtmpdump.git;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = " \
    CC='${CC}' LD='${LD} ${STAGING_LIBDIR}' \
    SYS=posix INC=-I=/usr/include DESTDIR=${D} \
    prefix=${prefix} libdir=${libdir} incdir=${includedir}/librtmp bindir=${bindir} mandir=${mandir}"


do_install() {
    install -d ${D}${libdir}
    oe_runmake DESTDIR=${D} install
}

FILES_${PN}-dev += "${sbindir}"
