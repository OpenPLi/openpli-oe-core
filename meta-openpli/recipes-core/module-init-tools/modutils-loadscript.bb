SECTION = "base"
DESCRIPTION = "Load hotplug modules at boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://modload.sh;beginline=2;endline=2;md5=3b6e5b2caf81c241a5956ed7691327ab"
SRC_URI = "file://modload.sh"
PV = "3"
PR = "r0"

INITSCRIPT_NAME = "modload.sh"
INITSCRIPT_PARAMS = "start 06 S ."

S = "${WORKDIR}"

inherit update-rc.d

do_compile () {
}

do_install () {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/modload.sh ${D}${sysconfdir}/init.d/
}
