SUMMARY = "partitions by name"
DESCRIPTION = "makes partitions names for internal flash in /dev/block/by-name/"
SECTION = "base"
LICENSE = "GPLv2"

require conf/license/openpli-gplv2.inc

SRC_URI = " \
       file://device-mapper.rules \
       file://mmc-dev-mapping.sh \
       "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -d ${D}${sysconfdir}/udev/scripts/
    install -m 0644 ${WORKDIR}/device-mapper.rules ${D}${sysconfdir}/udev/rules.d/device-mapper.rules
    install -m 0755 ${WORKDIR}/mmc-dev-mapping.sh ${D}${sysconfdir}/udev/scripts/mmc-dev-mapping.sh 
}

FILES_${PN} = "${sysconfdir}/udev"
