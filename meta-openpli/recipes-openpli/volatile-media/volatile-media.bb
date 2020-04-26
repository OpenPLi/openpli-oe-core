DESCRIPTION = "Mounts and populates a tmpfs over /media"
MAINTAINER = "PLi team"
require conf/license/openpli-gplv2.inc

inherit allarch

PV = "3"

SRC_URI = "file://volatile-media.sh file://volatiles.10_media"

PACKAGES = "${PN}"

FILES_${PN} = "${sysconfdir}"

do_compile() {
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/volatile-media.sh ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	ln -sf ../init.d/volatile-media.sh ${D}${sysconfdir}/rcS.d/S02volatile-media.sh
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 644 ${WORKDIR}/volatiles.10_media ${D}${sysconfdir}/default/volatiles/10_media
}
