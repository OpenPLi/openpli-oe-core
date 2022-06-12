SUMMARY = "Open Source Qt WebEngine HbbTV Browser"
PACKAGE_ARCH := "${MACHINE_ARCH}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
file://LICENSE;md5=e56d5762d06c562486323805b3ee7dde \
"

DEPENDS = "qtwebengine"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r1"

SRC_URI = "git://github.com/openhbbtvbrowser/openhbbtvbrowser.git;protocol=https \
    ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://0001-fix-key-events.patch', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://0001-mali-eglfs.patch', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'no-opengl', 'file://0001-linuxfb.patch', '', d)} \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit qmake5

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${B}/openhbbtvbrowser ${D}${bindir}
}

FILES_${PN} = "${bindir}"
