SUMMARY = "Early boot init for root filesystems in subdirectories"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://main.c \
    file://Makefile \
"

S = "${WORKDIR}"

inherit klibc

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}
    install -m 0755 ${S}/subdirboot ${D}/init

    install -d -m 0755 ${D}/dev
    mknod -m 0622 ${D}/dev/console c 5 1
}

FILES:${PN} = " \
    /init \
    /dev/console \
"
