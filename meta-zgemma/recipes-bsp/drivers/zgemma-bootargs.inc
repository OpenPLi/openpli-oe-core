SUMMARY = "${MACHINE} bootargs files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PR = "${SRCDATE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit zgemma-partition-deploy

ZGEMMA_LEGACY_DEPLOY_FILES = "bootargs.bin update_bootargs_${MACHINE}.bin ${MACHINE}/bootargs.bin"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/zgemma-bootargs-${HICHIPSET}-${SRCDATE}.zip"

S = "${WORKDIR}"

ALLOW_EMPTY:${PN} = "1"
do_configure[nostamp] = "1"

do_deploy() {
    install -d ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}
    install -m 0755 ${WORKDIR}/rescue_bootargs_${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/bootargs.bin
    install -m 0755 ${WORKDIR}/update_bootargs_${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/update_bootargs_${MACHINE}.bin
    install -m 0755 ${WORKDIR}/bootargs_${MACHINE}.bin ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/bootargs.bin
}

addtask deploy before do_build after do_install

INSANE_SKIP:${PN} += "already-stripped"
