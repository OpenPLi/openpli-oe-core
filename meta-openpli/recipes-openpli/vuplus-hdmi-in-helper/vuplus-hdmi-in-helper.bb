SUMMARY = "vuplus-hdmi-in-helper"
PRIORITY = "required"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://update_systemconfig_arm;md5=39018f970452e01743a00a4ff642c099"

PV = "1.0"

SRC_URI = " \
    file://update_systemconfig_arm \
    file://update_systemconfig.sh \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/init.d/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/update_systemconfig_arm ${D}${bindir}/update_systemconfig
    install -m 0755 ${WORKDIR}/update_systemconfig.sh ${D}${sysconfdir}/init.d/
}

inherit update-rc.d

INITSCRIPT_NAME = "update_systemconfig.sh"
INITSCRIPT_PARAMS = "start 90 3 ."

PACKAGE_ARCH = "${MACHINE_ARCH}"

# nothing to strip for debug here,
# update_systemconfig_arm is stripped
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
