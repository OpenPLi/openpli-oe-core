DESCRIPTION = "CiPlus-helper"
LICENSE = "CLOSED"
PR = "${SRCDATE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/ciplushelper-${HICHIPSET}-${SRCDATE}.zip \
	file://ciplushelper.sh \
	file://cicert.zip \
	"

S = "${WORKDIR}"

INITSCRIPT_NAME = "ciplushelper"
INITSCRIPT_PARAMS = "defaults 50"

FILES:${PN} += "${bindir} ${sysconfdir}"

inherit update-rc.d

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ciplushelper ${D}${bindir}
	install -m 0755 ciplushelper.sh ${D}${sysconfdir}/init.d/ciplushelper
	install -m 0755 cicert.bin ${D}${sysconfdir}
	install -m 0755 ciplus0_enable ${D}${sysconfdir}
	install -m 0755 ciplus1_enable ${D}${sysconfdir}
}

INSANE_SKIP:${PN} += "ldflags already-stripped"
