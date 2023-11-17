#
# Add init script, removed in OE 1.3
#

inherit update-rc.d

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
                   file://pcscd \
                   "

INITSCRIPT_NAME = "pcscd"
INITSCRIPT_PARAMS = "defaults"

do_install:append() {
    install -d ${D}/${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/pcscd ${D}/${sysconfdir}/init.d
}

FILES:${PN} += "/etc/init.d/pcscd"
