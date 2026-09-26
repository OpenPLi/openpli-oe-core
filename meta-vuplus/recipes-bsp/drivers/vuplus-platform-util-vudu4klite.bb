SUMMARY = "Platform Util drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH := "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

KV = "4.1.20"

PV = "${KV}+${SRCDATE}"
PR = "r0"

PROVIDES += "vuplus-platform-util"
RPROVIDES:${PN} += "vuplus-platform-util"

SRCDATE = "20260911"
SRC_URI[md5sum] = "ef99c20ee47698e5800a31099720f8e9"
SRC_URI[sha256sum] = "239dc10f9abdce9d963bd2e94cc0111bfb3ee2d545e501f62a1b18d9dd7de1f8"


# not available at code.vuplus.com
#SRC_URI = "https://code.vuplus.de/download/release/platform-util/platform-util-${MACHINE}-${SRCDATE}${PR}.zip"
# with RCU key patch
SRC_URI = "https://code.vuplus.de/download/release/platform-util/platform-util-${MACHINE}-with-keypatch-${SRCDATE}${PR}.zip"

INHIBIT_PACKAGE_STRIP = "1"

S = "${WORKDIR}/platform"

do_install() {
    install -d ${D}/home/root/platform
    install -m 0755 ${S}/* ${D}/home/root/platform
    install -d ${D}/etc/init.d
    install -m 0755 ${S}/${INITSCRIPT_NAME}.sysvinit ${D}/etc/init.d/${INITSCRIPT_NAME}
}

do_package_qa() {
}
do_populate_sysroot() {
}

FILES:${PN} = "/home/root/platform /etc/init.d"

RDEPENDS:${PN} += "update-rc.d (>= 0.8p)"

inherit update-rc.d

INITSCRIPT_PARAMS = "start 65 S . stop 90 0 ."
INITSCRIPT_NAME = "vuplus-platform-util"

INSANE_SKIP:${PN} += "already-stripped"
