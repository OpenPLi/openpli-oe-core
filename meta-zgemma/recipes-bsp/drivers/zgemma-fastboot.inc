SUMMARY = "${MACHINE} fastboot files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PR = "${SRCDATE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit zgemma-partition-deploy

ZGEMMA_LEGACY_DEPLOY_FILES = "fastboot.bin ${MACHINE}/fastboot.bin"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/zgemma-fastboot-${HICHIPSET}-${SRCDATE}.zip"

S = "${WORKDIR}"

ALLOW_EMPTY:${PN} = "1"
do_configure[nostamp] = "1"

do_deploy() {
    install -d ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}
    install -m 0755 ${WORKDIR}/fastboot_${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/fastboot.bin
    install -m 0755 ${WORKDIR}/fastboot_${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/fastboot.bin
}

addtask deploy before do_build after do_install

INSANE_SKIP:${PN} += "already-stripped"
