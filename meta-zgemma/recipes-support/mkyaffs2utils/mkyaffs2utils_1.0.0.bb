SUMMARY = "Yet Another Flash File System"
DESCRIPTION = "Tools for managing 'yaffs' file systems for bootmenu."

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://mkyaffs2image/mkyaffs2image.c;beginline=1;endline=16;md5=f3b785ac05385cddc907aa5688453b9b \
    file://mkyaffs2image/yportenv.h;beginline=1;endline=15;md5=49a5531df3851698d06736e8ccce2b87 \
"

inherit pkgconfig

PV = "1.0.0"

SRC_URI = " \
        file://mkyaffs2utils.tar.bz2 \
        file://0001-respect-toolchain.patch \
        "

S = "${WORKDIR}"

do_compile() {
    oe_runmake -C mkyaffs2image CC="${CC}" CPPFLAGS="${CPPFLAGS}" CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}"
}

FILES:${PN} += "${bindir}"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 mkyaffs2image/mkyaffs ${D}${bindir}
}

BBCLASSEXTEND = "native"
