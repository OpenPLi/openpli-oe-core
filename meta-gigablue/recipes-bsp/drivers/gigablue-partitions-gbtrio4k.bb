SUMMARY = "gbtrio4k partitions files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

SRCDATE = "20251028"
PR = "${SRCDATE}"

S = "${WORKDIR}/gbtrio4k-partitions-${SRCDATE}/partitions"

SRC_URI = "http://downloads.openpli.org/archive/gigablue/gbtrio4k-partitions-${SRCDATE}.zip"

ALLOW_EMPTY:${PN} = "1"
do_configure[nostamp] = "1"
do_install[noexec] = "1"

FILES:${PN} = "/usr/share"

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -d ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/platforms
    install -m 0755 ${S}/bootargs.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/boot.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/gbtrio4k_fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/platforms
    install -m 0755 ${S}/gbtrio4kpro_fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions/platforms
    install -m 0755 ${S}/apploader.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/pq_param.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/emmc_partitions.xml ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/baseparam.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/logo.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/deviceinfo.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
}

addtask deploy before do_build after do_install

SRC_URI[md5sum] = "de2353ffd45f69d6dc7666b25d089c40"
SRC_URI[sha256sum] = "10a911d65f184157fab8d4970c4eaca82afbf57d4bccad32f39c5d11a5cfe4dd"

INSANE_SKIP:${PN} += "already-stripped"
