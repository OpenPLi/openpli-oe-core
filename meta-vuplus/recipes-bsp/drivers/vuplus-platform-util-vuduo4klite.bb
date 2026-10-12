SUMMARY = "Platform Util drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH := "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

KV = "4.1.20"

PV = "${KV}+${SRCDATE}"
# update sysvinit for startup default LCD brightness
PR = "r3"

PROVIDES += "vuplus-platform-util"
RPROVIDES:${PN} += "vuplus-platform-util"

SRCDATE = "20260728"
SRC_URI[md5sum] = "d16622d0ababdbd7ef7f7b41ff0c61b9"
SRC_URI[sha256sum] = "8384c6e092a9933a3c34c061bb0e364211bc11ce1acf74024d871aaad0120555"


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
