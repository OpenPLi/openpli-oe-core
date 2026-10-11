SUMMARY = "${MACHINE} bootlogo files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PR = "${SRCDATE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit zgemma-partition-deploy

ZGEMMA_LEGACY_DEPLOY_FILES = "${MACHINE}/logo.img"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/zgemma-bootlogo-${HICHIPSET}-${SRCDATE}.zip"

S = "${WORKDIR}"

ALLOW_EMPTY:${PN} = "1"
do_configure[nostamp] = "1"

do_deploy() {
    install -d ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}
    if [ -f ${WORKDIR}/logo_${MACHINE}.img ]; then
        install -m 0755 ${WORKDIR}/logo_${MACHINE}.img ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/logo.img
    else
        install -m 0755 ${WORKDIR}/logo.img ${DEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/logo.img
    fi
}

addtask deploy before do_build after do_install

INSANE_SKIP:${PN} += "already-stripped"
