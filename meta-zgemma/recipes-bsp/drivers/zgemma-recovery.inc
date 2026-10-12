SUMMARY = "${MACHINE} parameters files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PR = "${SRCDATE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit zgemma-partition-deploy

ZGEMMA_LEGACY_DEPLOY_FILES = "${MACHINE}/recovery.bin ${MACHINE}/recovery.img"
ZGEMMA_LEGACY_DEPLOY_FILES:nand = "${MACHINE}/recovery.bin"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/zgemma-recovery-${HICHIPSET}-${SRCDATE}.zip"

S = "${WORKDIR}"

ALLOW_EMPTY:${PN} = "1"
do_configure[nostamp] = "1"

# Bootmenu with emmc flash expects recovery.img; the USB updater expects recovery.bin.

do_deploy() {
    install -d ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}
    install -m 0755 ${WORKDIR}/recovery-${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/recovery.bin
    install -m 0755 ${WORKDIR}/recovery-${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/recovery.img
}

do_deploy:nand() {
    install -d ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}
    install -m 0755 ${WORKDIR}/recovery-${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/recovery.bin
}

addtask deploy before do_build after do_install

INSANE_SKIP:${PN} += "already-stripped"
